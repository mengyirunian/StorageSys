package com.mengyirunian.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.mengyirunian.handler.DecimalTypeHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Strorage 数据库配置
 */
@EnableTransactionManagement
@Configuration
public class StorageDataConfig {

    @Value("${storage.data.username}")
    private String username;
    @Value("${storage.data.password}")
    private String password;
    @Value("${storage.data.jdbcurl}")
    private String jdbcurl;
    @Value("${storage.data.driver}")
    private String jdbcdriver;

    @Bean
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setUrl(jdbcurl);
        druidDataSource.setDriverClassName(jdbcdriver);
        return druidDataSource;
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        // 插件
        sessionFactory.setPlugins(new Interceptor[] { pageHelper() });
        sessionFactory.setTypeHandlers(typeHandler());
        return sessionFactory.getObject();
    }

    @Bean
    public TypeHandler<?>[] typeHandler() {
        return new TypeHandler[] { new DecimalTypeHandler() };
    }

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("dialect", "mysql");
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }

}
