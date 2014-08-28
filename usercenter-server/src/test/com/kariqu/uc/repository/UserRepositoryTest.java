package com.kariqu.uc.repository;

import com.kariqu.uc.domain.User;
import com.kariqu.uc.util.BCrypt;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test.xml"})
public class UserRepositoryTest{


    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRegisterUser() throws Exception {

        User user=new User();
        user.setPassword(BCrypt.hashpw("666666",BCrypt.gensalt()));
        user.setEmail("test2013@qq.com");
        user.setRegisterDate(new Date());
        user.setPhone("13266661111");
        user.setIsActive(true);
        user.setUserName("test2013");
        user.setGrade("A");
        int count= userRepository.saveOrUpdate(user);
        Assert.assertNotNull(count);
        User u= userRepository.findUserByName("test2014");
        Assert.assertNotNull(u);
        Assert.assertEquals("test2014",u.getUserName());

    }

    @Test
    public void testFindById() throws Exception {

       User user= userRepository.findById(7828l);
        Assert.assertNotNull(user);
        Assert.assertEquals("test2013",user.getUserName());
    }

    @Test
    public void testFindByUser() throws Exception {

       User user= userRepository.findUserByName("test");
        Assert.assertNotNull(user);
        Assert.assertEquals("test",user.getUserName());
    }

    //保存用户
    @Test
    public void testSaveOrUpdate() throws Exception {

        User user=new User();
        user.setId(7826l);
        user.setPassword(BCrypt.hashpw("666666",BCrypt.gensalt()));
        user.setEmail("test2014@qq.com");
        user.setRegisterDate(new Date());
        user.setPhone("13266661111");
        user.setIsActive(true);
        user.setUserName("test2014");
        user.setGrade("A");
       int count= userRepository.saveOrUpdate(user);
        Assert.assertNotNull(count);
       User u= userRepository.findUserByName("test2014");
       Assert.assertNotNull(u);
       Assert.assertEquals("test2014",u.getUserName());

    }

    @Test
    public void testFindUserByName() throws Exception {

        System.out.println(userRepository);
      // User user= userRepository.findUserByName("");
        //Assert.assertEquals("test",user.getUserName());
       // Assert.assertNull(user);
        //查询到数据的时候
        /*User user= userRepository.findUserByName("test");
        Assert.assertNotNull(user);
        Assert.assertEquals("test",user.getUserName());*/
        //查询部到数据时的情况
        User user= userRepository.findUserByName("adminTest");
        Assert.assertNull(user);
    }

    //根据电话号码查询是否被注册
    @Test
    public void testGetUserByPhone() throws Exception {

       //情况1:查询条件为空时
       /*User user= userRepository.getUserByPhone("");
        Assert.assertNull(user);*/
        //情况2：查询到数据时
        /*User user=userRepository.getUserByPhone("13265706791");
        Assert.assertNotNull(user);
        Assert.assertEquals("13265706791",user.getPhone(),user.getPhone()+"的电话号码已经被使用");*/
        //情况3：查询部到电话号码
        User user=userRepository.getUserByPhone("13112842061");
        Assert.assertNull(user);

    }

    //根据邮件地址查询邮件是否已被注册
    @Test
    public void testGetUserByEmail() throws Exception {

       //情况1：根据邮箱地址能查出邮箱
     /*  User user= userRepository.getUserByEmail("test@163.com");
        Assert.assertNotNull("邮件已使用!",user);
        Assert.assertEquals("test@163.com",user.getEmail());*/
        //情况2:查询条件传人空值
       /* User user=userRepository.getUserByEmail("");
        Assert.assertNull(user);*/
        //情况3:根据邮箱地址查询不到数据
        User user=userRepository.getUserByEmail("22@qq.com");
        Assert.assertNull(user);

    }


    //根据ip地址查询当天的注册次数
    @Test
    public void testSelectCountForSameIPToday() throws Exception {

      //查询到数据时
     /* int resultSize=  userRepository.selectCountForSameIPToday("127.0.0.1");
        System.out.println(resultSize);
        Assert.assertNotNull("ip注册次数不为空",resultSize);*/
        //查询条件为空时
       /* int count=userRepository.selectCountForSameIPToday("");
        Assert.assertNotNull(count);
        Assert.assertEquals(0,count);*/
        //查询不到数据时
        int size=userRepository.selectCountForSameIPToday("192.168.3.0");
        Assert.assertNotNull(size);
        Assert.assertEquals(0,size);
    }
}