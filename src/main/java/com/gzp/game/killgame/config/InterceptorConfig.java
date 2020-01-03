package com.gzp.game.killgame.config;

import com.gzp.game.killgame.api.Interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ws
 * @date 2019/12/26
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthenticationInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/login_do", "/", "/jquery/**", "/plugin/**");
    }

    @Bean
    public AuthenticationInterceptor getAuthenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

}
