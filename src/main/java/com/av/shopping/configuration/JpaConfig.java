package com.av.shopping.configuration;

import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * Author: Gaurav Karale
 * version 1.0.0
 * Date :- 06/05/2017
 * Info :-Data source configuration for AV shopping api 
 * */
@Configuration
public class JpaConfig {

	@Bean
    public DataSource dataSource() throws SQLException {
		
		 PoolProperties p = new PoolProperties();
         p.setUrl("jdbc:h2:mem:test;");
         p.setDriverClassName("org.h2.Driver");
         p.setUsername("sa");
         p.setPassword("");
         p.setJmxEnabled(true);
         p.setTestWhileIdle(false);
         p.setUseLock(true);
         p.setTestOnBorrow(true);
         p.setValidationQuery("SELECT 1");
         p.setTestOnReturn(false);
         p.setValidationInterval(30000);
         p.setTimeBetweenEvictionRunsMillis(30000);
         p.setMaxActive(100);
         p.setInitialSize(10);
         p.setMaxWait(10000);
         p.setRemoveAbandonedTimeout(60);
         p.setMinEvictableIdleTimeMillis(30000);
         p.setMinIdle(10);
         p.setLogAbandoned(true);
         p.setRemoveAbandoned(true);
         p.setJdbcInterceptors(
           "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
           "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
         DataSource datasource = new DataSource();
         datasource.setPoolProperties(p);
         return datasource ;
    }

}
