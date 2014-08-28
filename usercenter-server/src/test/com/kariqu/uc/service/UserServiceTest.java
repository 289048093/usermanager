package com.kariqu.uc.service;

import com.kariqu.uc.domain.User;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test.xml"})
public class UserServiceTest{

    @Autowired
    private  UserService userService;

    public void testRegisterUser() throws Exception {


    }

    public void testSaveOrUpdateUser() throws Exception {

    }

    public void testFindUserByName() throws Exception {

    }

    public void testGetUserByEmail() throws Exception {

    }

    @Test
    public void testGetUserByPhone() throws Exception {

        User user=userService.getUserByPhone("13265706791");
        Assert.assertEquals(user.getPhone(),"13265706791");
    }

    public void testSelectCountForSameIPToday() throws Exception {

    }
}