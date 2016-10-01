package com.liwei.shiro.service;

import com.liwei.shiro.model.Resource;

import java.util.List;

/**
 * Created by Liwei on 2016/9/19.
 */
public interface IResourceService {
    Integer add(Resource res);

    Integer update(Resource res);

    Integer delete(int id);

    Resource load(int id);

    List<Resource> listResource();
}
