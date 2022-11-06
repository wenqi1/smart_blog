package com.learn.blog.vo;

import com.learn.blog.model.Resource;
import lombok.Data;

import java.util.List;

/**
 * 资源树
 */
@Data
public class ResourceTreeVo extends Resource {
    // 子资源
    private List<ResourceTreeVo> childResources;
}
