package com.liwei.shiro.dao;

import com.liwei.shiro.model.Resource;

import java.util.List;

/**
 * Created by Liwei on 2016/9/18.
 */
public interface ResourceDao {
    Integer add(Resource res);

    Integer update(Resource res);

    Integer delete(int id);

    Resource load(int id);

    List<Resource> listResource();
}
