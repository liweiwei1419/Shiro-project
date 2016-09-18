package com.liwei.shiro.dao;

import com.liwei.shiro.model.Resource;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


import static org.junit.Assert.*;

/**
 * Created by Liwei on 2016/9/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class ResourceDaoTest {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(ResourceDaoTest.class);

    @Autowired
    private ResourceDao resourceDao;

    @Test
    public void testListResource() throws Exception {
        List<Resource> resources = resourceDao.listResource();
        for(Resource resource:resources){
            logger.debug(resource.toString());
        }
    }
}