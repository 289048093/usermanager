/**
 * Created by HuaLei.Du on 2014/5/28.
 */

var Ejs = window.Ejs || {};

(function ($) {
    "use strict";

    (function () {
        var loginPage = $('.login-page'),
            agreement = $('.agreement');

        loginPage.on('click', '.link-agreement', function (E) {
            E.preventDefault();
            agreement.show();
        });

        loginPage.on('click', '.agreement .btn-close', function () {
            agreement.hide();
        });

    }());

    // 取字条串长度(汉字相当于2个字符)
    function strLength(str) {
        return str.replace(/[^\x00-\xff]/g, "rr").length;
    }

    // 重载图形验证码
    function reloadImageCode() {
        var myself = $('.imageCode');
        myself.attr('src', myself.attr('src') + '?' + new Date().getTime());
    }

    $('.reload-imageCode').on('click', function () {
        reloadImageCode();
    });

    // 隐藏label
    function hideLabel(items) {
        var i,
            itemsCount = items.length;

        for (i = 0; i < itemsCount; i++) {
            if ($('#' + items[i].id).val() != '') {
                $('label[for=' + items[i].id + ']').hide();
            }
        }

    }

    // 延迟隐藏label
    function delayHideLabel(items) {
        $(window).on('load', function () {
            setTimeout(function () {
                hideLabel(items);
            }, 400);
        });
    }

    // 显示表单提交成功消息
    function showFormSuccessMsg(successHtml) {
        var loginPageMain = $('.login-page-main');
        loginPageMain.find('h3').hide();
        loginPageMain.find('form').hide();
        loginPageMain.find('.form-success').hide();
        loginPageMain.append(successHtml);
    }

    // 用户登录
    function login() {

        var options = {
            form: $('#login-form'),
            label: true,
            items: [
                {
                    id: 'username',
                    //blankText: '手机号/邮箱/用户名',
                    allowBlank: false,
                    allowBlankText: '账号不能为空'
                },
                {
                    id: 'password',
                    //blankText: '请输入密码',
                    allowBlank: false,
                    allowBlankText: '密码不能为空'
                }
            ]
        },
            loginFormValidate = new Ejs.user.FormValidate(options);

        loginFormValidate.init();

        delayHideLabel(options.items);
    }

    // 用户注册
    function register() {

        var btn = $('#btn-register');

        $('#rememberName').on('click', function () {

            var self = $(this);

            if (!self[0].checked) {
                btn.attr('disabled', true);
            } else {
                btn.removeAttr('disabled');
            }
        });        
        var options = {
            form: $('#register-form'),
            defaultTipsPosition: 'bottom',
            defaultTipsWidth: 120,
            label: true,
            items: [
                {
                    id: 'username',
                    //blankText: '用户名/邮箱/手机号',
                    allowBlank: false,
                    minLength: 4,
                    vtype: 'Account',
                    tipsText: '长度在4-20位字符，可由中文、英文、数字及"_"、"-"组成',
                    allowBlankText: '账号不能为空'
                },
                {
                    id: 'password',
                    //blankText: '请输入密码',
                    itemType: 'password',
                    allowBlank: false,
                    allowBlankText: '密码不能为空',
                    tipsText: '6-16位字符，可由英文、数字及“_”组成',
                    minLength: 6,
                    maxLength: 16
                },
                {
                    id: 'rePassword',
                    //blankText: '请再次输入密码',
                    itemType: 'password',
                    allowBlank: false,
                    allowBlankText: '确认密码不能为空',
                    tipsText: '请再次输入密码',
                    validator: function (v) {
                        var passwordValue = $('#password').val();
                        if (passwordValue != v) {
                            return '两次输入的密码不一至';
                        }
                        return true;
                    }
                },
                {
                    id: 'code',
                    //blankText: '输入验证码',
                    allowBlank: false,
                    allowBlankText: '验证码不能为空',
                    minLength: 4
                }
            ]
        },
            RegisterFormValidate;

        RegisterFormValidate = new Ejs.user.FormValidate(options, function () {
            var form = options.form,
                msgEle = $('#errormsg-register'),
                myAccountUrl = $('input[name=service]').val();
            btn.attr('disabled', true);
            btn.text('正在提交...');
            $.ajax({
                type: 'POST',
                url: form.attr('action'),
                async: false,
                data: form.serialize(),
                dataType: 'JSON',
                success: function (response) {
                    var successHtml;
                    if (response.success) {
                        var linkLab = "?"
                        if(myAccountUrl.indexOf("?")>-1) {
                            linkLab = "&";
                        }
                        var ticketUrl = linkLab +  'ticket=' + response.data.ticket;
                        successHtml = '<div class="form-success">' +
                            '<p>恭喜您，账号注册成功！</p>' +
                            '<a href="' + myAccountUrl + ticketUrl + '" class="e-btn btn-default">回到首页</a> &nbsp;' +
                            '</div>';
                        showFormSuccessMsg(successHtml);
                    }
                    else {
                        $("#errormsg-" + response.data.ids).html(response.msg);
                        $("#" + response.data.ids).addClass("form-text-error");
                        btn.text('注册');
                        btn.removeAttr('disabled');
                        reloadImageCode();
                    }
                },
                error: function () {
                    msgEle.html('服务器错误');
                }
            });
        });
        RegisterFormValidate.init();

        delayHideLabel(options.items);
    }

    // 找回密码
    function findPassword() {

        // 重新发送修改密码邮件
        $('.login-page').on('click', '.resendMail', function (E) {
            E.preventDefault();
            var self = $(this),
                url = self.attr('href');

            $.ajax({
                type: 'POST',
                url: url,
                async: false,
                dataType: 'json',
                success: function (response) {

                    if (response.success) {
                        Ejs.tip(self, 'limit_tip', '邮件已发送!', 32);
                        return;
                    }

                    Ejs.tip(self, 'limit_tip', response.msg, 32);

                },
                error: function () {
                    Ejs.tip(self, 'limit_tip', '系统故障,请重试!', 32);
                }
            });
        });

        var options = {
            form: $('#fp-form'),
            label: true,
            items: [
                {
                    id: 'email',
                    vtype: 'Email',
                    //blankText: '请输入注册时填写的邮箱',
                    allowBlank: false,
                    allowBlankText: '邮箱不能为空'
                },
                {
                    id: 'code',
                    //blankText: '输入验证码',
                    allowBlank: false,
                    allowBlankText: '验证码不能为空'
                }
            ]
        },
            fpFormValidate = new Ejs.user.FormValidate(options, function () {
                var form = options.form,
                    msgEle = $('#errormsg-code');

                $.ajax({
                    type: 'POST',
                    url: form.attr('action'),
                    async: false,
                    data: form.serialize(),
                    dataType: 'JSON',
                    success: function (response) {
                        var resend,
                            successHtml,
                            backUrl;

                        if (response.success) {
                            resend = response.data.resend;
                            backUrl = response.data.backUrl;
                            successHtml = '<div class="form-success">' +
                                '<p>系统已成功发送一封验证邮件到您的邮箱，请注意查收！</p>';

                            if (backUrl && response.data.backUrl.length > 1) {
                                successHtml += '<a href="' + backUrl + '" class="e-btn btn-default">点击进入</a> &nbsp;';
                            }

                            successHtml += '<a href="' + resend + '" class="e-btn btn-grey resendMail">重新发送</a></div>';
                            showFormSuccessMsg(successHtml);
                        } else {
                            msgEle.html(response.msg);
                            reloadImageCode();
                            $('#code').val('');
                        }

                    },
                    error: function () {
                        msgEle.html('服务器错误');
                    }
                });
            });

        fpFormValidate.init();

        delayHideLabel(options.items);
    }

    // 重置密码
    function resetPassword() {
        var options = {
            form: $('#nw-form'),
            defaultTipsPosition: 'bottom',
            label: true,
            items: [
                {
                    id: 'password',
                    //blankText: '请输入您的新密码',
                    allowBlank: false,
                    allowBlankText: '密码不能为空',
                    tipsText: '请至少使用6个字符',
                    minLength: 6,
                    maxLength: 16
                },
                {
                    id: 'rePassword',
                    //blankText: '请再次输入您的新密码',
                    tipsText: '请再次输入您的新密码',
                    allowBlank: false,
                    allowBlankText: '请再次输入您的新密码',
                    validator: function (v) {
                        var passwordValue = $('#password').val();
                        if (passwordValue != v) {
                            return '两次输入的密码不一至';
                        }
                        return true;
                    }
                }
            ]
        },
            nwFormValidate = new Ejs.user.FormValidate(options, function () {
                var form = options.form,
                    msgEle = $('#errormsg-nw');

                $.ajax({
                    type: 'POST',
                    url: form.attr('action'),
                    async: false,
                    data: form.serialize(),
                    dataType: 'JSON',
                    success: function (response) {
                        var successHtml;

                        if (response.success) {
                            successHtml = '<div class="form-success">' +
                                '<p>恭喜您，新密码设置成功！</p>' +
                                '<a href="' + EJS.ToPageLogin + '" class="e-btn btn-default">去登录</a> &nbsp;' +
                                '<a href="' + EJS.HomeUrl + '" class="e-btn btn-grey" class="resendMail">回到首页</a>' +
                                '</div>';
                            showFormSuccessMsg(successHtml);

                        } else {
                            msgEle.html(response.msg);
                        }

                    },
                    error: function () {
                        msgEle.html('服务器错误');
                    }
                });
            });

        nwFormValidate.init();

        delayHideLabel(options.items);
    }

    Ejs.user.login = login;
    Ejs.user.register = register;
    Ejs.user.findPassword = findPassword;
    Ejs.user.resetPassword = resetPassword;

}(jQuery));

