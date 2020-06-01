package com.demo.bbs.config;

import com.demo.bbs.interceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private Interceptor interceptorConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        InterceptorRegistration registration = registry.addInterceptor(new Interceptor()); //拦截的对象会进入这个类中进行判断

        registration.addPathPatterns("/public","/reply/**","/user","/admin","/listUser","deleteUser");//拦截的路径

        registration.excludePathPatterns("/index","/","/login","/error","/static/**","/logout","classpath:/resources/**");//放行

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/user/**").addResourceLocations("file:G:/java ee/bbs/src/main/resources/static/user/");
    }


}
