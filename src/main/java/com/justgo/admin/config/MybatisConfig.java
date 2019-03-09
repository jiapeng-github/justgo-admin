package com.justgo.admin.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.github.pagehelper.autoconfigure.PageHelperProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * mybatis配置
 */
@Configuration
@MapperScan(basePackages = "com.justgo.admin.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfig {

    //@Primary
    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource druidDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public Resource[] getMapperXML() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        return resolver.getResources("classpath:mappers/*.xml");
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("druidDataSource") DataSource dataSource, @Qualifier("getMapperXML") Resource[] mapperXML) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setMapperLocations(mapperXML);
        sessionFactory.setTypeAliasesPackage("com.justgo.admin.entity");
        sessionFactory.setDataSource(dataSource);
        //tk.mybatis.mapper.session.Configuration
        tk.mybatis.mapper.session.Configuration configuration = new tk.mybatis.mapper.session.Configuration();
        //可以对 MapperHelper 进行配置后 set
        MapperHelper mapperHelper = new MapperHelper();
        configuration.setMapperHelper(mapperHelper);
        //设置为 tk 提供的 Configuration
        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();
    }

//    @Bean
    public PageHelperProperties pageHelperProperties(){
        PageHelperProperties pageHelperProperties = new PageHelperProperties();
        pageHelperProperties.setHelperDialect("mysql");
        pageHelperProperties.setReasonable("false");
        pageHelperProperties.setSupportMethodsArguments("true");
        pageHelperProperties.setParams("count=countSql");
        return pageHelperProperties;
    }

}
