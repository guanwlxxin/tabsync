package com.bryer.tabsync.config;

import cn.hutool.db.Session;
import cn.hutool.db.dialect.impl.MysqlDialect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author zhangnan@yansou.org
 */
@Configuration
public class SessionConfig {

    private final DataSource srcDataSource;
    private final DataSource destDataSource;

    public SessionConfig(@Qualifier("srcMysqlDataSource") DataSource srcDataSource,@Qualifier("destMysqlDataSource") DataSource destDataSource) {
        this.srcDataSource = srcDataSource;
        this.destDataSource = destDataSource;
    }

    @Bean
    @Qualifier("srcSession")
    public Session srcSession() {
        System.out.println("源会话:" + srcDataSource);
        return new Session(srcDataSource,new MysqlDialect());
    }

    @Bean
    @Qualifier("destSession")
    public Session destSession() {
        System.out.println("目标会话:" + destDataSource);
        return new Session(destDataSource,new MysqlDialect());
    }
}
