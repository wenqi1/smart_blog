package com.learn.blog.service;

import com.learn.blog.model.Resource;
import com.learn.blog.vo.ResourceTreeVo;

import java.util.List;

/**
 * 资源service层
 */
public interface ResourceService {
    /**
     * 添加资源
     *
     * @param resource resource
     */
    void addResource(Resource resource);

    /**
     * 查询整个资源树
     *
     * @return List<ResourceTreeVo>
     */
    List<ResourceTreeVo> queryResourceTree();

    /**
     * 递归资源按照树结构组装
     *
     * @param resources 资源
     * @param parentId 父id
     * @return List<ResourceTreeVo>
     */
     List<ResourceTreeVo> recursionResource(List<Resource> resources, long parentId);
}
