package com.kariqu.uc.util;

import com.kariqu.uc.domain.User;
import com.kariqu.uc.exception.CheckUserException;
import com.kariqu.uc.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 检测用户信息 包括：验证码 用户名 邮箱
 */
public class CheckUser {

    private final Log logger = LogFactory.getLog(CheckUser.class);

    private UserService userService;

    public void checkImageCode(HttpServletRequest request, String imageCode) throws CheckUserException {
        String e_tag="code";
        Object imageCodeInSession = request.getSession().getAttribute("imageCode");
        if (null == imageCode || null == imageCodeInSession || !imageCode.equals(imageCodeInSession.toString())) {
            throw new CheckUserException("验证码错误",e_tag);
        }
    }

    public void checkUserLogin(User user, String password) throws CheckUserException {
        checkPasswordByStyle(password);
        if (user == null) {
            throw new CheckUserException("账户不存在！");
        }
        Boolean delete = user.getDelete();
        if (delete != null && delete) {
            throw new CheckUserException("账户不存在或已被删除！");
        }
        Boolean active = user.getIsActive();
        if (active == null || !active) {
            throw new CheckUserException("账户未激活，请激活后再进行登入操作！");
        }
        Boolean hasForbidden = user.getHasForbidden();
        if (hasForbidden != null && hasForbidden) {
            throw new CheckUserException("账户被禁用，无法进行登入操作！");
        }
        // 验证用户密码是否正确
        String dbPass = user.getPassword();
        if( dbPass ==null) {
            logger.error("登入系统异常 ~ username:" + user.getUserName()+", 数据库密码为空！" );
            throw new CheckUserException("登入系统异常！");
        }
        if (!BCrypt.checkpw(password, dbPass)) {
            throw new CheckUserException("密码错误！");
        }
    }

    public void checkPasswordByStyle(String password) throws CheckUserException {
        String e_tag="password";
        if (StringUtils.isBlank(password)) {
            throw new CheckUserException("密码不能为空",e_tag);
        }
        int passwordLength = password.length();
        if (!(passwordLength >= 6 && passwordLength <= 16)) {
            throw new CheckUserException("密码长度应为6-16位",e_tag);
        }
    }

    public void checkUserName(String userName) throws CheckUserException {
        String e_tag="username";
        if (StringUtils.isBlank(userName)) {
            throw new CheckUserException("用户名不能为空",e_tag);
        }
        int userNameLength = ChineseLength.length(userName);
        if (CheckUtils.checkEmail(userName)) {
            if (!(userNameLength >= 5 && userNameLength <= 50)) {
                throw new CheckUserException("邮箱能由5-50个字符组成",e_tag);
            }
            checkUserEmail(userName);
        } else {
            if (!(userNameLength >= 4 && userNameLength <= 20)) {
                throw new CheckUserException("用户名只能由4-20个字符组成",e_tag);
            }
        }
        User user = userService.findUserByName(userName);
        if (null != user) {
            throw new CheckUserException("该用户已存在",e_tag);
        }
    }

    public void checkRegisterNumber(HttpServletRequest request) throws CheckUserException {
        String e_tag="username";
        String registerIP = IpTools.getIpAddress(request);
        int count = userService.selectCountForSameIPToday(registerIP);
        if (logger.isDebugEnabled()) {
            logger.debug(registerIP + "注册次数：" + count);
        }
        if (count >= 5) {
            throw new CheckUserException("该IP地址当天注册超过限制",e_tag);
        }
    }

    public void checkUserPassword(User user, String oldPassword, String newPassword) throws CheckUserException {
        if (user == null) {
            throw new CheckUserException("账户不存在");
        }
        // 校验原始密码
        if (!(user.getIsActive() && BCryptUtil.check(oldPassword, user.getPassword()))) {
            throw new CheckUserException("原始密码有误");
        }
        // 校验密码格式
        checkPasswordByStyle(newPassword);
    }

    public void checkUserEmail(String email) throws CheckUserException {
        String e_tag="username";
        if (logger.isDebugEnabled()) {
            logger.debug("注册邮箱为：" + email);
        }
        if (StringUtils.isBlank(email)) {
            throw new CheckUserException("邮箱不能为空",e_tag);
        }
        if (!CheckUtils.checkEmail(email)) {
            throw new CheckUserException("邮箱格式有误",e_tag);
        }
        User user = userService.getUserByEmail(email);
        if (null != user) {
            throw new CheckUserException("该邮箱已被使用",e_tag);
        }
    }

    public void checkPhone(String phone) throws CheckUserException {
        String e_tag="username";
        if (!CheckUtils.checkPhone(phone)) {
            throw new CheckUserException("不是有效的手机号",e_tag);
        }
        User user = userService.getUserByPhone(phone);
        if (user != null) {
            throw new CheckUserException("手机号码已被使用",e_tag);
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
