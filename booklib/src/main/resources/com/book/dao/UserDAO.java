package com.book.dao;

import com.book.model.User;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

import java.util.List;

@DAO
public interface UserDAO {

    @SQL("select count(*) from user")
    public int rows();

    @SQL("select uuid, name, cardid, sex, birthday, homeaddr, status, email, password, login_name, create_time, actived_time, groups from user where login_name=:1")
    public User getByLoginName(String loginName);

    @SQL("select uuid, name, cardid, sex, birthday, homeaddr, status, email, password, login_name, create_time, actived_time, groups from user where uuid=:1")
    public User get(String userUid);

    /**
     * 分页显示
     *
     * @param preLimit
     * @param limit
     * @return
     */
    @SQL("select uuid, name, cardid, sex, birthday, homeaddr, status, email, password, login_name, create_time, actived_time, groups from user order by id desc limit :1, :2")
    public List<User> find(int preLimit, int limit);

    @SQL("delete from user where uuid=:1")
    public void delete(String userUid);

    /**
     * 注销
     *
     * @param userUid
     */
    @SQL("update user set status=2 where uuid=:1")
    public void canceled(String userUid);

    @ReturnGeneratedKeys
    @SQL("insert into user (uuid, login_name, email, password, name, cardid, sex, birthday, homeaddr, groups) values (replace(uuid(), '-', ''), :1.loginName, :1.email, :1.password, :1.name, :1.cardid, :1.sex, :1.birthday, :1.homeAddr, :1.groups)")
    public int save(User user);

    @SQL("update user set login_name=:1.loginName, password=:1.password, name=:1.name, cardid=:1.cardid, email=:1.email, status=:1.status, sex=:1.sex, birthday=:1.birthday, homeaddr=:1.homeAddr where uuid=:1.uuid")
    public void update(User user);

    @SQL("update user set status=:1.status, actived_time=UNIX_TIMESTAMP(:1.activedTime) where uuid=:1.uuid")
    public void updateStatus(User user);

}
