/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunledarams.mobilewsapi;

import io.swagger.annotations.SwaggerDefinition;
import io.swagger.models.Contact;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author TremendocLimited
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport  {
    
    
    @Bean
    public Docket apiDocket() {
        
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
//                .pathMapping("/api")
                .build().apiInfo(metaData())
                .pathMapping("/api");
      
        return docket;
    }
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Tremendoc API")
                .description("Rest API documentation for Tremendoc")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
               
                .build();
    }
    
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
	
		registry.addResourceHandler("swagger-ui.html")
      .addResourceLocations("classpath:/META-INF/resources/");
	}
    
    

//	@Override
//	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//		 registry.addResourceHandler("swagger-ui.html")
//         .addResourceLocations("classpath:/META-INF/resources/");
//
//          registry.addResourceHandler("**/webjars/**")
//         .addResourceLocations("classpath:/META-INF/resources/webjars/");
//	}
    
    

}
