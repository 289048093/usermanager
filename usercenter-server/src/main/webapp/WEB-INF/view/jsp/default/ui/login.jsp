<%--
  Created by IntelliJ IDEA.
  User: Koala
  Date: 14-7-3
  Time: 下午5:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>登录-喀日曲用户中心</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/account.css"/>"/>

    <!--[if IE 6]>
    <script type="text/javascript" src="https://login.ejushang.com/static/js/DD_belatedPNG.js"></script>
    <script>DD_belatedPNG.fix('.ie6png');</script>
    <![endif]-->
</head>
<body>
<input name="isRedirect" type="hidden" id="isRedirect" value="false">
<!-- 登录页 -->
<div class="login-page">
    <div class="login-bg">
        <div class="e-wrapper">
            <div class="login-page-main">
                <div class="head"><span>喀日曲用户中心</span></div>
                <h3>登录</h3>
                <form:form method="post" id="login-form" commandName="${commandName}" htmlEscape="true">
                    <form:errors path="*" id="msg" cssClass="errormsg" element="div" />
                    <input type="hidden" name="lt" value="${loginTicket}" />
                    <input type="hidden" name="execution" value="${flowExecutionKey}" />
                    <input type="hidden" name="_eventId" value="submit" />
                    <ul>
                        <li class="item-row">
                            <label for="username" class="form-label">手机号/邮箱/用户名</label>
                            <c:if test="${not empty sessionScope.openIdLocalId}">
                                <strong>${sessionScope.openIdLocalId}</strong>
                                <input type="hidden" id="username" name="username" value="${sessionScope.openIdLocalId}" />
                            </c:if>
                            <c:if test="${empty sessionScope.openIdLocalId}">
                                <spring:message code="screen.welcome.label.netid.accesskey" var="userNameAccessKey" />
                                <form:input cssClass="form-text" cssErrorClass="error" id="username" size="25" tabindex="1"
                                            accesskey="${userNameAccessKey}" path="username" autocomplete="false" htmlEscape="true" />
                            </c:if>
                            <span class="errormsg" id="errormsg-username"></span>
                        </li>
                        <li class="item-row">
                            <label for="password" class="form-label">请输入密码</label>
                            <form:password cssClass="form-text" cssErrorClass="error" id="password" size="25" tabindex="2" path="password"
                                           accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" />
                            <span class="errormsg" id="errormsg-password"></span>
                        </li>

                        <li class="options-row">
                            <input id="warn" name="warn" value="true" type="checkbox" />
                            <label for="warn">记住用户名</label>
                        </li>
                        <li class="btns">
                            <button type="submit" name="submit" class="e-btn btn-login login_submit">安全登录</button>
                        </li>
                        <li><a href="https://login.ejushang.com/user/userPassword">忘记密码？</a></li>
                    </ul>

                </form:form>

                <div class="cooperation-login clearfix">
                    <h4>使用其他帐号快速登录</h4>
                    <ul>
                        <li><a href="http://www.ejushang.com/user/jointLogin?flag=QQ&backUrl=http%3A%2F%2Fwww.ejushang.com" class="qq"
                               title="使用QQ账号登录">QQ</a></li>
                        <li><a href="http://www.ejushang.com/user/jointLogin?flag=Sina&backUrl=http%3A%2F%2Fwww.ejushang.com" class="sina"
                               title="使用新浪账号登录">新浪</a></li>
                        <li><a href="http://www.ejushang.com/user/loginByAlipay?backUrl=http%3A%2F%2Fwww.ejushang.com" class="alipay"
                               title="使用支付宝账号登录">支付宝</a></li>
                    </ul>
                </div>

                <div class="help-block">
                    <a href="<c:url value="/register?service=${param.service}" />" title="点击跳转到注册页面！" class="link">您还没有账号？快速10秒注册&gt;</a>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- /登录页 -->
<!-- 网站底部 -->
<div class="e-footer" id="e-footer">
    <div class="e-wrapper">
        <p class="text-center">&copy; 2014 ejushang.com 版权所有 | <a href="http://www.miibeian.gov.cn/" target="_blank">粤ICP备12069686号-1</a></p>

        <p class="text-center"><a href="http://www.ejushang.com/about/33.html">关于我们</a>｜客服热线 400-9933-178</p>
    </div>
</div>
<!-- 网站底部 END -->
<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/user/FormValidate.js"/>"></script>
<script src="<c:url value="/resources/js/user/login.js"/>"></script>
<script>
    Ejs.user.login();
</script>
<div style="display: none">
    <script src="https://login.ejushang.com/static/js/statistics.js?1403784862338"></script>
</div>
</body>
</html>