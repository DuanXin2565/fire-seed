package com.jc.fire.configurer;

import java.util.Properties;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.jc.fire.core.ProjectConstant;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * Mybatis & Mapper & PageHelper 配置
 */
@Configuration
public class MybatisConfigurer {

    /**
     * 使 application.properties配置生效，如果不主动配置，由于@Order配置顺序不同，
     * 将导致配置不能及时生效 多数据源配置驼峰法生效
     *
     * @return 数据源
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration globalConfiguration() {

        return new org.apache.ibatis.session.Configuration();

    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource, org.apache.ibatis.session.Configuration config) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTypeAliasesPackage(ProjectConstant.MODEL_PACKAGE);
        factory.setConfiguration(config);
//        //配置分页插件，详情请查阅官方文档
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        //分页尺寸为0时查询所有纪录不再执行分页
//        properties.setProperty("pageSizeZero", "true");
//        //页码<=0 查询第一页，页码>=总页数查询最后一页
//        properties.setProperty("reasonable", "true");
//        //支持通过 Mapper 接口参数来传递分页参数
//        properties.setProperty("supportMethodsArguments", "true");
//        pageHelper.setProperties(properties);

        //添加插件
//        factory.setPlugins(new Interceptor[]{pageHelper});

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        return factory.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        mapperScannerConfigurer.setBasePackage(ProjectConstant.MAPPER_PACKAGE);

        //配置通用Mapper，详情请查阅官方文档
        Properties properties = new Properties();
        properties.setProperty("mappers", ProjectConstant.MAPPER_INTERFACE_REFERENCE);
        //insert、update是否判断字符串类型!='' 即 test="str != null"表达式内是否追加 and str != ''
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);

        return mapperScannerConfigurer;
    }

}

