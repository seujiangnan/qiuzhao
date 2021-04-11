package com.jiangnan.qiuzhao.config;

import com.jiangnan.qiuzhao.service.Impl.UserTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserTService userTService;
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/addUser.html").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/login.do").permitAll()
                .anyRequest().authenticated();
        http.formLogin() 								// 定义当需要用户登录时候，转到的登录页面。
                .loginPage("/login.html")	 					// 设置登录页面
                .loginProcessingUrl("/login.do") 			// 自定义的登录接口
                .defaultSuccessUrl("/companylist.html").permitAll()		// 登录成功之后，默认跳转的页面
                .and().csrf().disable();
        http.logout()
        .logoutUrl("/logout.do")
        .logoutSuccessUrl("/login.html");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userTService).passwordEncoder(passwordEncoder());
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("jiangnan").password(new BCryptPasswordEncoder().encode("123456"));
    }
}
