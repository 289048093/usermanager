package com.kariqu.uc.exception;

/**
 * @author: Koala
 * @Date: 14-7-4 下午6:15
 * @Version: 1.0
 */
public class CheckUserException extends Exception {

    //错误信息在页面上所显示的TAG
    private String tag;

    public CheckUserException(){}

    public CheckUserException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public CheckUserException(String message, String tag) {
        super(message);
        this.tag = tag;
    }

    public CheckUserException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public CheckUserException(String message, Throwable cause, String tag) {
        super(message, cause);
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
