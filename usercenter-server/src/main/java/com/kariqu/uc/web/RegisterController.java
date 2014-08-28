package com.kariqu.uc.web;

import com.kariqu.uc.common.JsonResult;
import com.kariqu.uc.domain.User;
import com.kariqu.uc.exception.CheckUserException;
import com.kariqu.uc.service.UserService;
import com.kariqu.uc.util.BCrypt;
import com.kariqu.uc.util.CheckUser;
import com.kariqu.uc.util.CheckUtils;
import com.kariqu.uc.util.IpTools;
import org.jasig.cas.CentralAuthenticationService;
import org.jasig.cas.authentication.principal.Service;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.jasig.cas.util.DefaultUniqueTicketIdGenerator;
import org.jasig.cas.web.support.ArgumentExtractor;
import org.jasig.cas.web.support.CookieRetrievingCookieGenerator;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.webflow.execution.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author: Koala
 * @Date: 14-7-4 下午3:32
 * @Version: 1.0
 */
public class RegisterController extends AbstractController {

    @NotNull
    private String registerView;

    @NotNull
    private String loginView;

    private UserService userService;

    private CheckUser checkUser;

    @NotNull
    private CentralAuthenticationService centralAuthenticationService;

    @NotNull
    private CookieRetrievingCookieGenerator ticketGrantingTicketCookieGenerator;

    @NotNull
    private CookieRetrievingCookieGenerator warnCookieGenerator;

    @NotNull
    private List<ArgumentExtractor> argumentExtractors;

    public RegisterController() {
    }

    public RegisterController(UserService userService, CheckUser checkUser) {
        this.userService = userService;
        this.checkUser = checkUser;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals(RequestMethod.GET.toString())) {
            String service = request.getParameter("service");
            //注册成功返回首页，首页参数由客户端传参过来，如果没有加默认返回到登录页
            final String toFromUrl = (service != null && !"".equals(service)) ? service : request.getContextPath() + "/login";
            ModelAndView modelAndView = new ModelAndView(this.registerView);
            modelAndView.addObject("service", toFromUrl);
            return modelAndView;
        } else {
            User user = new User();
            String imageCode = request.getParameter("imageCode");
            String password = request.getParameter("password");
            String userName = request.getParameter("userName");
            try {
                // 校验用户名
                checkUser.checkUserName(userName);
                if (CheckUtils.checkEmail(userName.trim())) {
                    user.setEmail(userName.trim());
                } else if (CheckUtils.checkPhone(userName.trim())) {
                    checkUser.checkPhone(userName.trim());
                    user.setPhone(userName.trim());
                }
                // 校验注册次数
                checkUser.checkRegisterNumber(request);
                // 校验密码格式
                checkUser.checkPasswordByStyle(password);
                // 校验验证码
                checkUser.checkImageCode(request, imageCode);
            } catch (CheckUserException e) {
                new JsonResult(e.getMessage()).addData("ids", e.getTag()).toJson(response);
                return null;
            }
            user.setUserName(userName.trim());
            user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            user.setRegisterDate(new Date());
            user.setRegisterIP(IpTools.getIpAddress(request));
            user.setIsActive(true);
            try {
                userService.saveOrUpdateUser(user);
//                new JsonResult(true, "注册成功").toJson(response);
                UsernamePasswordCredentials credentials = new UsernamePasswordCredentials();
                credentials.setUsername(user.getUserName());
                credentials.setPassword(password);
                String ticketGrantingTicket = centralAuthenticationService.createTicketGrantingTicket(credentials);
                ticketGrantingTicketCookieGenerator.addCookie(request, response, ticketGrantingTicket);
                warnCookieGenerator.addCookie(request, response, ticketGrantingTicket);
                final Service service = WebUtils.getService(this.argumentExtractors, request);
                final String serviceTicketId = this.centralAuthenticationService.grantServiceTicket(ticketGrantingTicket, service, credentials);
//                WebUtils.putServiceTicketInRequestScope(context, serviceTicketId);
//                putWarnCookieIfRequestParameterPresent(context);
                new JsonResult(true, "注册成功").addData("ticket", serviceTicketId).toJson(response);
                return null;
            } catch (Exception e) {
                logger.error("用户" + user.getUserName() + "注册失败！", e);
                new JsonResult(false, "注册失败请您联系客服人员进行处理！").addData("ids", "username").toJson(response);
                return null;
            }
        }
    }

    private void putWarnCookieIfRequestParameterPresent(final RequestContext context) {
        final HttpServletResponse response = WebUtils.getHttpServletResponse(context);

        if (StringUtils.hasText(context.getExternalContext().getRequestParameterMap().get("warn"))) {
            this.warnCookieGenerator.addCookie(response, "true");
        } else {
            this.warnCookieGenerator.removeCookie(response);
        }
    }

    public String getLoginView() {
        return loginView;
    }

    public void setLoginView(String loginView) {
        this.loginView = loginView;
    }

    public String getRegisterView() {
        return registerView;
    }

    public void setRegisterView(String registerView) {
        this.registerView = registerView;
    }

    public void setCentralAuthenticationService(CentralAuthenticationService centralAuthenticationService) {
        this.centralAuthenticationService = centralAuthenticationService;
    }

    public void setTicketGrantingTicketCookieGenerator(CookieRetrievingCookieGenerator ticketGrantingTicketCookieGenerator) {
        this.ticketGrantingTicketCookieGenerator = ticketGrantingTicketCookieGenerator;
    }

    public void setWarnCookieGenerator(CookieRetrievingCookieGenerator warnCookieGenerator) {
        this.warnCookieGenerator = warnCookieGenerator;
    }


    public void setArgumentExtractors(List<ArgumentExtractor> argumentExtractors) {
        this.argumentExtractors = argumentExtractors;
    }
}
