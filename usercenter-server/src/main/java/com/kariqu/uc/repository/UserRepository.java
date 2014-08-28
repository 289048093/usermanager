package com.kariqu.uc.repository;

import com.kariqu.uc.domain.User;
import com.kariqu.uc.util.GenerateUid;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: SanAction
 * @Date:2014/7/4 14:58
 * @Version: 1.0.
 */
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        DataSource dataSource=jdbcTemplate.getDataSource();
        simpleJdbcInsert=new SimpleJdbcInsert(dataSource).withTableName("user")
                .usingGeneratedKeyColumns("id");
        namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);

    }

    public UserRepository(SimpleJdbcInsert simpleJdbcInsert) {
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    public int registerUser(User user) {
        //新增用户信息
      /*  String insertSql = "insert into user(userName,password,email,isActive,registerDate,registerIP,hasForbidden,isDelete,loginTime" +
                "loginCount,accountType,grade,expenseTotal,phone,uid) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(insertSql, user.getUserName(), user.getPassword(), user.getEmail(), user.getActive(), user.getRegisterDate(),
                user.getRegisterIP(), user.getHasForbidden(), user.getDelete(),
                user.getLoginTime(), user.getLoginCount(), user.getAccountType(), user.getGrade(), user.getExpenseTotal(), user.getPhone(), user.getUid());*/

        SqlParameterSource parameters = new BeanPropertySqlParameterSource(user);
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        user.setId(newId.longValue());
        return newId.intValue();
    }

    public User findById(Long id) {
        if (null != id) {
            String sql = "select * from user where id=?";
            return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
        }
        return null;
    }

    public List<User> findByUser(User user) {
        return null;
    }

    public int saveOrUpdate(User user) {

        SqlParameterSource parameters = new BeanPropertySqlParameterSource(user);
        if (null != user.getId() && !"".equals(user.getId())) {
            //更新用户信息
         /*   String updateSql = "update user set userName=?, password=?,email=?,isActive=?,registerDate=?,registerIP=?,hasForbidden=?,isDelete=?,loginTime=?," +
                    "loginCount=?,accountType=?,grade=?,expenseTotal=?,phone=?,uid=? where id=?";
            return jdbcTemplate.update(updateSql, user.getUserName(), user.getPassword(), user.getEmail(), user.getActive(), user.getRegisterDate(),
                    user.getRegisterIP(), user.getHasForbidden(), user.getDelete(),
                    user.getLoginTime(), user.getLoginCount(), user.getAccountType(), user.getGrade(), user.getExpenseTotal(), user.getPhone(),
                    user.getUid(), user.getId());*/

            String updateSql = "update user set userName=:userName, password=:password,email=:email,isActive=:isActive,registerDate=:registerDate,registerIP=:registerIP,hasForbidden=:hasForbidden,isDelete=:isDelete,loginTime=:loginTime," +
                    "loginCount=:loginCount,accountType=:accountType,grade=:grade,expenseTotal=:expenseTotal,phone=:phone,uid=:uid where id=:id";
            return namedParameterJdbcTemplate.update(updateSql,parameters);

        } else {
            //新增用户信息
           /* String insertSql = "insert into user(userName,password,email,isActive,registerDate,registerIP,hasForbidden,isDelete,loginTime," +
                    "loginCount,accountType,grade,expenseTotal,phone,uid) " +
                    "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            user.setUid(GenerateUid.creatUid());//设置uid
            return jdbcTemplate.update(insertSql, user.getUserName(), user.getPassword(), user.getEmail(), user.getActive(), user.getRegisterDate(),
                    user.getRegisterIP(), user.getHasForbidden(), user.getDelete(),
                    user.getLoginTime(), user.getLoginCount(), user.getAccountType(), user.getGrade(), user.getExpenseTotal(), user.getPhone(), user.getUid());*/


            user.setUid(GenerateUid.creatUid());//设置uid
            Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
            user.setId(newId.longValue());
            return newId.intValue();
        }

    }

    public User findUserByName(String userName) {
        if (!StringUtils.isEmpty(userName)) {
            String sql = "select * from user where userName=?";
            List<User> users=jdbcTemplate.query(sql,new UserMapper(),userName);
            if(null!=users&&users.size()>0){
                return users.get(0);
            }else{
                return null;
            }
        }
        return null;
    }

    class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            //对查询出来的数据进行封装
            User user = new User();
            user.setId(rs.getLong("id"));//设置id
            user.setUserName(rs.getString("userName"));//设置用户名
            user.setPassword(rs.getString("password"));//
            user.setEmail(rs.getString("email"));//设置邮件
            user.setIsActive(rs.getBoolean("isActive"));//
            user.setRegisterDate(rs.getDate("registerDate"));//设置注册时间
            user.setRegisterIP(rs.getString("registerIP"));//设置Ip
            user.setHasForbidden(rs.getBoolean("hasForbidden"));
            user.setDelete(rs.getBoolean("isDelete"));
            user.setLoginTime(rs.getDate("loginTime"));//设置登陆时间
            user.setLoginCount(new BigInteger(rs.getString("loginCount") == null ? "0" : rs.getString("loginCount")));//设置登陆次数
            user.setAccountType(rs.getString("accountType"));
            user.setGrade(rs.getString("grade"));//设置账号等级
            user.setExpenseTotal(new BigInteger(rs.getString("expenseTotal") == null ? "0" : rs.getString("expenseTotal")));//
            user.setPhone(rs.getString("phone"));//设置电话号码
            user.setUid(rs.getString("uid"));
            return user;
        }
    }

    /**
     * 校验手机号码是否被使用
     *
     * @param phone
     * @return User
     */
    public User getUserByPhone(String phone) {
        String sql = "select * from user where phone=?";
        List<User> users = jdbcTemplate.query(sql, new UserMapper(), phone);
        if (users != null && users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

    }

    /**
     * 校验邮件账号是否被使用
     *
     * @param email
     * @return User
     */
    public User getUserByEmail(String email) {
        if (StringUtils.isNotEmpty(email)) {
            String sql = "select * from user where email=?";
            List<User> users=jdbcTemplate.query(sql, new UserMapper(), email);
            if(null!=users&&users.size()>0){
                return users.get(0);
            }else {
                return null;
            }
        }
        return null;
    }

    public int selectCountForSameIPToday(String ip) {
        if (StringUtils.isNotEmpty(ip)) {
            String sql = "select count(registerIP) from user " +
                    "where registerIP=? and date_format(registerDate,'%Y%m%d')=date_format(now(),'%Y%m%d')";
            int count = jdbcTemplate.queryForInt(sql, ip);
            return count;
        }
        return 0;
    }

}
