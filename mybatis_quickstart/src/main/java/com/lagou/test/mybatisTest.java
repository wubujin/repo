package com.lagou.test;

import com.lagou.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class mybatisTest {

    /*
        快速入门测试方法
     */
    @Test
    public void mybatisQuickStart() throws IOException {

        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4.执行sql  参数 ：statementid: namespace.id
        List<User> users = sqlSession.selectList("userMapper.findAll");

        //5.遍历打印结果
        for(User user : users){
            System.out.println(user);
        }

        //6.关闭资源
        sqlSession.close();
    }

    /*
        测试新增用户
     */
    @Test
    public void testSave() throws IOException {

        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //new 一个User对象
        User user = new User();
        user.setUsername("Ami coni");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("北京市海淀");

        //4.执行sql  参数 ：statementid: namespace.id
        int insert = sqlSession.insert("userMapper.saveUser", user);

        //5.打印结果
        System.out.println(insert);

        //手动提交事务
//        sqlSession.commit();

        //6.关闭资源
        sqlSession.close();
    }

    /*
        测试更新用户
     */
    @Test
    public void testUpdate() throws IOException {

        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //new 一个User对象
        User user = new User();
        user.setId(4);
        user.setUsername("Jack");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("北京朝阳");

        //4.执行sql  参数 ：statementid: namespace.id
        int update = sqlSession.update("userMapper.updateUser", user);

        //5.打印结果
        System.out.println(update);

        //手动提交事务
        sqlSession.commit();

        //6.关闭资源
        sqlSession.close();
    }

    /*
        测试删除用户
     */
    @Test
    public void testDelete() throws IOException {

        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();


        //4.执行sql  参数 ：statementid: namespace.id
        int delete = sqlSession.delete("userMapper.deleteUser", 4);

        //5.打印结果
        System.out.println(delete);

        //手动提交事务
        sqlSession.commit();

        //6.关闭资源
        sqlSession.close();
    }

}
