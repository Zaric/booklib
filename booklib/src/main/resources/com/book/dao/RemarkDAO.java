package com.book.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

import com.book.model.Remark;

@DAO
public interface RemarkDAO {

    @SQL("select count(*) from remark where book_uid=:1")
    public int rows(String bookUid);

    // 标注一个@SQL，写入你的sql语句
    // 不能写select * from remark，这样的后果可能会因为数据库增加了一个字段，但Remark没有相应字段的属性，Jade将抛出异常
    // 参数以冒号开始，:1表示第一个参数
    @SQL("select uuid, user_name, book_uid, essay, create_time from remark where book_uid=:1 order by id desc limit :2, :3")
    public List<Remark> findByBook(String bookUuid, int preLimit, int limit);

    @SQL("delete from remark where book_uid=:1")
    public void deleteByBook(String bookUid);

    // 返回int表示变更的条数，就这个示例而言，应该就是返回1
    @SQL("delete from remark where uuid=:1")
    public int delete(String remarkUid);

    // @ReturnGeneratedKeys用于获取自增生成的那个id
    // :1.userName表示第一个参数的userName属性
    @ReturnGeneratedKeys
    @SQL("insert into remark (uuid, user_name, book_uid, essay) values (replace(uuid(), '-', ''), :1.userName, :1.bookUid, :1.essay)")
    public int save(Remark remark);
}
