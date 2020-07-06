package com.joinbright.taskservice.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author alin
 * @date 2020/6/16 14:02
 */

@Configuration
@MapperScan(basePackages = "com.joinbright.taskservice.mapper.zentao", sqlSessionTemplateRef = "zenTaoSqlSessionTemplate")
public class ZenTaoDataSourceConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.zendao")
    public DataSource zenTaoDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory zenTaoSqlSessionFactory(@Qualifier("zenTaoDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/zentao/*.xml"));
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager zenTaoTransactionManager(@Qualifier("zenTaoDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate zenTaoSqlSessionTemplate(@Qualifier("zenTaoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
