package com.aguo.config;

import com.aguo.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UUserService userService;

    @Autowired
    SignInFailureHandler signInFailureHandler;


    @Bean
    public PasswordEncoder passwordEncoder() {
//        加密类型
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers(
                HttpMethod.GET,
                "/",
                "/css/**",
                "/js/**",
                "/fonts/**",
                "/img/**",
                "favicon.ico",
                "/index",
                "/index.html",
                "/code/**"
        ).antMatchers(
                HttpMethod.POST,
                "/uUser/register"
        );
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/manage/**").access("hasAnyRole('manage','admin')")
                .antMatchers("/admin/**").access("hasAnyRole('admin')")
                .anyRequest().authenticated() // 用户访问其它URL都必须认证后访问（登录后访问）
                .and().formLogin()
                .loginPage("/").loginProcessingUrl("/login").permitAll()
                .successForwardUrl("/loginSuccess").permitAll()
                .failureHandler(signInFailureHandler).permitAll()
//                .failureForwardUrl("/loginError").permitAll()
                .permitAll() // 开启表单登录并配置登录接口
                .and().csrf().disable();

        //        注销
        http.logout().logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
        //记住我
        http.rememberMe()
                .rememberMeParameter("rememberMe")
                .tokenValiditySeconds(259200);//超时时间
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }
}
