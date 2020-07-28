package com.jc.fire.configurer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <Description> <br>
 *
 * @author duan.xin<br>
 * @version 1.0<br>
 * @taskId <br>
 * @Date 2020/7/27 17:18
 * @see com.jc.fire.configurer <br>
 * @since V8.1<br>
 */

@Configuration
@EnableSwagger2
//是否开启swagger，正式环境一般是需要关闭的（避免不必要的漏洞暴露！），可根据springboot的多环境配置进行设置
@ConditionalOnProperty(name = "swagger.enable",  havingValue = "true")
public class SwaggerConfig {


    @Bean

    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)

                .apiInfo(apiInfo())

                .select()

                .apis(RequestHandlerSelectors.basePackage("com.jc.fire.web"))

                .paths(PathSelectors.any())

                .build();

    }


    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()

                .title("Spring Boot中使用Swagger2构建RESTful APIs")

                .description("test swagger")

                .termsOfServiceUrl("http")

                .contact("@duan.xin")

                .version("1.0")

                .build();

    }


}


