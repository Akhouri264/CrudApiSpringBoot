//package swaggerConfig;
//
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import springfox.documentation.annotations.ApiIgnore;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@EnableSwagger2
//@Configuration
//public class SwaggerConfig {
//	String val="com.example.springproject.controllers";
//    @Bean
//    public Docket api() {
//
//        Set<String> protocolSet = new HashSet<String>();
//        protocolSet.add("http");
//        protocolSet.add("https");
//        return new Docket(DocumentationType.SWAGGER_2)
//        		.select()
//        		.apis(RequestHandlerSelectors.
//        				basePackage(val)
//        				).paths(PathSelectors.any()).build();
//    }
//};