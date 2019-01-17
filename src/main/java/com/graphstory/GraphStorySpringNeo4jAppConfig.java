package com.graphstory;

import com.graphstory.interceptor.DisplayInterceptor;
import com.graphstory.interceptor.SecureDisplayInterceptor;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableWebMvc
@Configuration
@EnableNeo4jRepositories(basePackages = "com.graphstory.repository")
@EnableTransactionManagement
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:mustache.properties"),
        @PropertySource("classpath:neo4j.properties")
})
@EnableSwagger2
@EnableAutoConfiguration(exclude = MustacheAutoConfiguration.class)
public class GraphStorySpringNeo4jAppConfig implements WebMvcConfigurer {

    @Value(value = "${com.graphstory.neo4j.uri}")
    private String uri;

    @Bean
    public SessionFactory sessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory(getConfiguration(), "com.graphstory.model");
    }

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
                //TODO use this ONLY for development
                //.autoIndex("assert")
                .uri(uri)
                .credentials("USER", "PW")
                .build();
        return configuration;
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

        }
    }

    @Bean
    public Docket swaggerSettings() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Graph Story Data Service")
                .description("Data Service endpoints for Graph Story.")
                .build();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(displayInterceptor()).addPathPatterns("/**").excludePathPatterns("/api/**","/app/**","/v2/api-docs/**","/swagger-ui.html#!/**","/callback","/css/**","/font-awesome/**","/fonts/**","/home/**","/img/**","/js/**","/lib/**","/favicon.ico");
        registry.addInterceptor(secureDisplayInterceptor()).addPathPatterns("/app/**").excludePathPatterns("/","/api/**","/v2/api-docs/**","/swagger-ui.html#!/**","/callback","/css/**","/font-awesome/**","/fonts/**","/home/**","/img/**","/js/**","/lib/**","/favicon.ico");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).favorParameter(false)
                .parameterName("mediaType")
                .ignoreAcceptHeader(false)
                .useJaf(false)
                .defaultContentType(MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML).mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Bean
    public SecureDisplayInterceptor secureDisplayInterceptor() {
        return new SecureDisplayInterceptor();
    }

    @Bean
    public DisplayInterceptor displayInterceptor() {
        return new DisplayInterceptor();
    }
}
