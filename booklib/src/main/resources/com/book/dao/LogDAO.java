package com.book.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

import com.book.model.Log;

/**
 * @author zhangzuoqiang
 * @Todo
 * @Modify
 */
@DAO
public interface LogDAO {

    @SQL("select count(*) from log where user_name=:1")
    public int rows(String userName);

    @SQL("select uuid, user_name, resource_pattern, resource_id, success, remarks, create_time from log")
    public List<Log> find();

    @SQL("select uuid, user_name, resource_pattern, resource_id, success, remarks, create_time from log where user_name=:1")
    public List<Log> find(String userName);

    /**
     * @param limit
     * @return
     * @Todo 查询前几行的数据
     * @Modify
     */
    @SQL("select uuid, user_name, resource_pattern, resource_id, success, remarks, create_time from log where user_name=:1 order by id desc limit :2")
    public List<Log> find(String userName, int limit);

    /**
     * @param userName
     * @param preLimit 当前页码*页面容量-1
     * @param limit    页面容量
     * @return
     * @Todo 分页显示
     */
    @SQL("select uuid, user_name, resource_pattern, resource_id, success, remarks, create_time, ip from log where user_name=:1 order by id desc limit :2, :3")
    public List<Log> find(String userName, int preLimit, int limit);

    @ReturnGeneratedKeys
    @SQL("insert into log (uuid, user_name, resource_pattern, resource_id, success, remarks, ip) values (replace(uuid(), '-', ''), :1.userName, :1.resourcePattern, :1.resourceId, :1.success, :1.remarks, :1.ip)")
    public int save(Log log);

    @SQL("delete from log where uuid=:1")
    public void delete(String logUid);
}
