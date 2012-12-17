package com.book.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

import com.book.model.Book;

/**
 * @author zhangzuoqiang
 * @Todo 必须是接口，并且以大写DAO结尾 必须标注@DAO，DAO中有一个catalog属性，对于大部分人来说，这个都是没用的
 * @Modify
 */
@DAO
public interface BookDAO {

    /**
     * @param bookUid
     * @return
     * @Todo 根据BookID从数据库查询出Book
     * @Modify
     */
    @SQL("select uuid, name, price, author, content, create_time from book where uuid = :1")
    public Book get(String bookUid);

    /**
     * @return
     * @Todo 获取记录条数
     */
    @SQL("select count(*) from book")
    public int rows();

    /**
     * @param name
     * @return
     * @Todo 从数据库中查询出书名一样的书，一般来说书名可能重复，但是书名和作者不能同时重复
     * @Modify
     */
    @SQL("select uuid, name, price, author, content, create_time from book where name = :1")
    public List<Book> getBooksByName(String name);

    /**
     * @param limit
     * @return
     * @Todo 查询前几行的数据
     * @Modify
     */
    @SQL("select uuid, name, price, author, content, create_time from book order by id desc limit :1")
    public List<Book> find(int limit);

    @SQL("select uuid, name, price, author, content, create_time from book order by id desc limit :1, :2")
    public List<Book> find(int preLimit, int limit);

    @SQL("update book set name=:1.name, content=:1.content, price=:1.price, author=:1.author where uuid=:1.uuid")
    public void update(Book book);

    @ReturnGeneratedKeys
    @SQL("insert into book (uuid, name, price, author, content) values (replace(uuid(), '-', ''), :1.name, :1.price, :1.author, :1.content)")
    public int save(Book book);

    @SQL("delete from book where uuid = :1")
    public void delete(String bookUid);
}
