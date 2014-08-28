package com.kariqu.uc.service;

import com.kariqu.uc.domain.User;
import com.kariqu.uc.repository.UserRepository;
import com.kariqu.uc.util.BCrypt;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author: SanAction
 * @Date:2014/7/4 16:40
 * @Version: 1.0.
 */
@Transactional
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int registerUser(User user) {
        if(null!=user){
            user.setRegisterDate(new Date());//注册时间
            user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));//设置密码
            return userRepository.registerUser(user);
        }
      return 0;
    }

    public int saveOrUpdateUser(User user) {
        if(null!=user){
//            user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
            return userRepository.saveOrUpdate(user);
        }
       return 0;
    }

    /**
     * 校验用户名是否被注册
     * @param userName
     * @return
     */
    @Transactional(readOnly = true)
    public User findUserByName(String userName) {
        return userRepository.findUserByName(userName);
    }

    /**
     * 校验邮件是否被注册
     * @param email
     * @return
     */
    @Transactional(readOnly = true)
    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    /**
     * 校验电话号码是否被注册
     * @param phone
     * @return
     */
    @Transactional(readOnly = true)
    public User getUserByPhone(String phone){
        return userRepository.getUserByPhone(phone);
    }


    /**
     * 校验同一个Ip地址
     * +-------------                                                  86y026y7622222222222.....7yuyt 23.
     *
     * 2
     * 62+3065当天注册次数不能多于5次
     * @param ip
     * @return
     */
    @Transactional(readOnly = true)
    public int  selectCountForSameIPToday(String ip){
        return userRepository.selectCountForSameIPToday(ip);
    }
}
