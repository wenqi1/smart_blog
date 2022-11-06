package com.learn.blog.controller;

import com.learn.blog.enums.ResponseCode;
import com.learn.blog.model.APIResponse;
import com.learn.blog.model.BasicResponse;
import com.learn.blog.model.Resource;
import com.learn.blog.service.ResourceService;
import com.learn.blog.vo.ResourceTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源controller层
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    /**
     * 添加资源
     *
     * @param resource resource
     * @return BasicResponse
     */
    @PostMapping("/add")
    public BasicResponse addResource(@Validated Resource resource) {
        resourceService.addResource(resource);
        return new BasicResponse(ResponseCode.SUCCESS);
    }

    /**
     * 查询整个资源树
     *
     * @return APIResponse<List<ResourceTreeVo>>
     */
    @GetMapping (value = "/queryResourceTree")
    public APIResponse<List<ResourceTreeVo>> queryTreeMenu() {
        return new APIResponse<>(ResponseCode.SUCCESS, resourceService.queryResourceTree());
    }
}
