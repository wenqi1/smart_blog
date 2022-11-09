package com.learn.blog.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.learn.blog.annotation.DataSourceSwitch;
import com.learn.blog.dao.ResourceDao;
import com.learn.blog.enums.DataSourceNames;
import com.learn.blog.enums.ResponseCode;
import com.learn.blog.exception.SmartException;
import com.learn.blog.model.Resource;
import com.learn.blog.service.ResourceService;
import com.learn.blog.utils.SnowflakeIdUtils;
import com.learn.blog.vo.ResourceTreeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private SnowflakeIdUtils snowflakeIdUtils;

    @Override
    @DataSourceSwitch(name = DataSourceNames.POSTGRESQL)
    public void addResource(Resource resource) {
        // 如果添加的资源为根节点，parentId为0
        if(resource.getParentId() == 0){
            // 根节点的层级为1
            resource.setLevel(1);
            // 根节点路径为空
            resource.setPath(null);
        }else{
            Resource parentResource = null;
            try {
                parentResource = resourceDao.queryResourceById(resource.getParentId());
            } catch (Exception e) {
                throw new SmartException(ResponseCode.FAILURE, e);
            }
            if(parentResource == null){
                throw new SmartException(ResponseCode.PARENT_RESOURCE_NOT_EXIST, new Object[]{resource.getParentId()});
            }
            // 设置层级
            resource.setLevel(parentResource.getLevel() + 1);
            // 设置根路径，根路径为所有父节点的id用','拼接
            if(!StringUtils.isEmpty(parentResource.getPath())){
                resource.setPath(parentResource.getPath() + "," + parentResource.getId());
            }else{
                resource.setPath(parentResource.getId().toString());
            }
        }

        resource.setId(snowflakeIdUtils.nextId());

        try {
            resourceDao.insert(resource);
        } catch (Exception e) {
            throw new SmartException(ResponseCode.FAILURE, e);
        }
    }

    @Override
    @DataSourceSwitch(name = DataSourceNames.POSTGRESQL)
    public List<ResourceTreeVo> queryResourceTree() {
        // 获取所有根资源
        Example example = new Example(Resource.class);
        example.setOrderByClause("level,sort");
        // 通过level和sort排序查询出所有资源
        try {
            List<Resource> resources = resourceDao.selectByExample(example);
            return recursionResource(resources, 0L);
        } catch (Exception e) {
            throw new SmartException(ResponseCode.FAILURE, e);
        }
    }

    public List<ResourceTreeVo> recursionResource(List<Resource> resources, long parentId){
        List<ResourceTreeVo> resultList = new ArrayList<>();
        if(CollectionUtils.isEmpty(resources)){
            return resultList;
        }

        for (Resource resource : resources) {
            if(parentId == resource.getParentId()){
                ResourceTreeVo resourceTreeVo = new ResourceTreeVo();
                BeanUtils.copyProperties(resource, resourceTreeVo);
                //递归出指定父的所有子菜单
                List<ResourceTreeVo> childList = recursionResource(resources, resource.getId());
                if(!CollectionUtils.isEmpty(childList)){
                    resourceTreeVo.setChildResources(childList);
                }
                resultList.add(resourceTreeVo);
            }
        }

        return resultList;
    }
}
