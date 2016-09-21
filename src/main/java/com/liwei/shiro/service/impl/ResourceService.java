package com.liwei.shiro.service.impl;

import com.liwei.shiro.cache.BaseCacheService;
import com.liwei.shiro.dao.ResourceDao;
import com.liwei.shiro.model.Resource;
import com.liwei.shiro.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Liwei on 2016/9/19.
 */
@Service
public class ResourceService extends BaseCacheService implements IResourceService {

    @Autowired
    private ResourceDao resourceDao;


    @Override
    public Integer add(Resource res) {
        return resourceDao.add(res);
    }

    @Override
    public Integer update(Resource res) {
        return resourceDao.update(res);
    }

    @Override
    public Integer delete(int id) {
        return resourceDao.delete(id);
    }

    @Override
    public Resource load(int id) {
        return resourceDao.load(id);
    }

    @Override
    public List<Resource> listResource() {
        List<Resource> resources = (List<Resource>)super.get("allResources");
        if(resources==null){
            resources = resourceDao.listResource();
            super.put("allResources",resources);
        }
        return resources;
    }

    public ResourceService() {
        super.setCacheName("resourceServiceCache");
    }
}
