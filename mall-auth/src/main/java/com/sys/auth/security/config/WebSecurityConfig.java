package com.sys.auth.security.config;

import com.sys.auth.security.provider.sysadminprovider.SysAdminAuthenticationProvider;
import com.sys.auth.security.provider.sysuserprovider.SysUserAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author rensf
 * @date 2023/2/20
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService sysAdminDetailsService;
    private final UserDetailsService sysUserDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/oauth/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic().disable()
            .csrf().disable();
    }

    /**
     * 认证管理对象
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAdminAuthenticationProvider())
            .authenticationProvider(daoUserAuthenticationProvider());
    }

    /**
     * 用户名密码认证授权供应器（管理员）
     */
    @Bean("SysAdminDetailsProvider")
    public SysAdminAuthenticationProvider daoAdminAuthenticationProvider() {
        SysAdminAuthenticationProvider provider = new SysAdminAuthenticationProvider();
        provider.setUserDetailsService(sysAdminDetailsService);
        return provider;
    }

    /**
     * 用户名密码认证授权供应器（用户）
     */
    @Bean("SysUserDetailsProvider")
    public SysUserAuthenticationProvider daoUserAuthenticationProvider() {
        SysUserAuthenticationProvider provider = new SysUserAuthenticationProvider();
        provider.setUserDetailsService(sysUserDetailsService);
        return provider;
    }

    /**
     * 密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
