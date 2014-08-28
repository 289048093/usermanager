package com.kariqu.uc.handler;

import com.kariqu.uc.domain.User;
import com.kariqu.uc.exception.CheckUserException;
import com.kariqu.uc.service.UserService;
import com.kariqu.uc.util.CheckUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.BadCredentialsAuthenticationException;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

/**
 * @Author Channel
 * @Date 2014/7/4
 * @Version: 1.0
 */
public class AuthenticationHandler extends org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler {

    private final Log logger = LogFactory.getLog(AuthenticationHandler.class);

    @NotNull
    private UserService userService;

    @NotNull
    private CheckUser checkUser;

    protected final boolean authenticateUsernamePasswordInternal(final UsernamePasswordCredentials credentials) throws AuthenticationException {
        final String username = getPrincipalNameTransformer().transform(credentials.getUsername());
        final String password = credentials.getPassword();
        try {
            if (username == null) {
                throw new BadCredentialsAuthenticationException("请输入用户名！");
            }
            if (password == null) {
                throw new BadCredentialsAuthenticationException("请输入登入密码！");
            }
            // 登入验证
            User user = userService.findUserByName(username);
            checkUser.checkUserLogin(user, password);
            // 登入成功后更新登入信息
            BigInteger loginCount = user.getLoginCount();
            if (loginCount != null) {
                loginCount = new BigInteger("0");
            }
            user.setLoginCount(loginCount.add(new BigInteger("1")));
            user.setLoginTime(new Date());
            userService.saveOrUpdateUser(user);
            return true;
        } catch (CheckUserException e) {
            throw new BadCredentialsAuthenticationException(e.getMessage());
        } catch (Exception e) {
            logger.error("登入系统异常 ~ username:" + username, e);
            throw new BadCredentialsAuthenticationException("登入系统异常！");
        }
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setCheckUser(CheckUser checkUser) {
        this.checkUser = checkUser;
    }
}