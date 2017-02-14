package lut.lijihu.airtransport.configuration;

import lut.lijihu.airtransport.core.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by kj on 2017/2/13.
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
    @Bean
    public LoginInterceptor loginInterceptor(){return new LoginInterceptor();}

    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html")
                .excludePathPatterns("/user/login");

    }
}
