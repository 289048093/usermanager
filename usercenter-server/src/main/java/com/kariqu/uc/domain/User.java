package com.kariqu.uc.domain;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author: SanAction
 * @Date:2014/7/4 14:22
 * @Version: 1.0.
 */
public class User implements Serializable {

    private Long id;

    //用户名
    private String userName;

    //密码
    private String password;

    private String email;//邮箱

    private Boolean isActive;

    private Date registerDate;//注册时间
    private String registerIP;//注册Ip

    private Boolean hasForbidden;//是否禁止登陆
    private Boolean isDelete;
    private Date loginTime;//登陆时间
    private BigInteger loginCount;//登陆次数
    private String accountType;//账号类型
    private String grade;//账号等级
    private BigInteger expenseTotal;
    private BigInteger pointTotal;
    private String phone;//电话号码
    private String uid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterIP() {
        return registerIP;
    }

    public void setRegisterIP(String registerIP) {
        this.registerIP = registerIP;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Boolean getHasForbidden() {
        return hasForbidden;
    }

    public void setHasForbidden(Boolean hasForbidden) {
        this.hasForbidden = hasForbidden;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public BigInteger getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(BigInteger loginCount) {
        this.loginCount = loginCount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public BigInteger getExpenseTotal() {
        return expenseTotal;
    }

    public void setExpenseTotal(BigInteger expenseTotal) {
        this.expenseTotal = expenseTotal;
    }

    public BigInteger getPointTotal() {
        return pointTotal;
    }

    public void setPointTotal(BigInteger pointTotal) {
        this.pointTotal = pointTotal;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", registerDate=" + registerDate +
                ", registerIP='" + registerIP + '\'' +
                ", hasForbidden=" + hasForbidden +
                ", isDelete=" + isDelete +
                ", loginTime=" + loginTime +
                ", loginCount=" + loginCount +
                ", accountType='" + accountType + '\'' +
                ", grade='" + grade + '\'' +
                ", expenseTotal=" + expenseTotal +
                ", pointTotal=" + pointTotal +
                ", phone='" + phone + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
