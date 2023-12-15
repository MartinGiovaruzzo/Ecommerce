package com.aceleracion.ecommercegyl.security;

import com.aceleracion.ecommercegyl.security.jwt.JwtAuthorizationFilter;
import com.aceleracion.ecommercegyl.security.jwt.JwtProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        AuthenticationManagerBuilder auth = http.getSharedObject(
                AuthenticationManagerBuilder.class
        );
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
        AuthenticationManager authenticationManager = auth.build();

        http.cors();
        http.csrf().disable();
        http.authenticationManager(authenticationManager);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeHttpRequests()
                .antMatchers("/backsale/create","/branch/create","/ecommerce/authentication/sign-in", "/backsale/all","/brand/all","/brand/allstatustrue",
                        "/city/all","/city/allstatustrue","/customer/all","/customer/allstatustrue",
                        "/customer/nameandlastname","/discountrate/allstatustrue","/discountrate/all","/invoice/all",
                        "/invoicetype/all","/invoicetype/allbystatustrue",
                        "/paymentmethod/all","/paymentmethod/allbystatustrue","/product/all","/product/allbystatustrue",
                        "/producttype/","/producttype/allbystatustrue","/province/all","/role/all",
                        "/branchtype/all","/branchtype/allstatustrue",  "/branch/all", "/branch/address","/branch/allstatustrue","/transport/")
                .permitAll()

                .regexMatchers("/user/manager/.+","/branch/[0-9]+","/branch/name/.+","/branch/update/[0-9]+","/branch/changestatus/[0-9]+",
                        "/branchtype/name/.+","/branchtype/changestatus/.+","/brand/changestatus/.+","/invoice/allbypaymentmethod/[0-9]+","/city/[0-9]+","/backsale/[0-9]+",
                        "/brand/name/.+","/brand/[0-9]+","/customer/.+","/customer/.+",
                        "/customer/changestatus/.+","/discountrate/[0-9]+","/invoice/[0-9]+",
                        "/invoice/allbyinvoicetype/[0-9]+", "/invoice/allbydate/.+",
                        "/invoice/allbybranch/[0-9]+", "/invoice/allbycustomer/.+",
                        "/invoice/allbyuser/.+", "/invoicetype/[0-9]+",
                        "/paymentmethod/[0-9]+","/product/[0-9]+",
                        "/product/branch/[0-9]+","/product/branch/[0-9]+/producttype/[0-9]+",
                        "/product/branch/[0-9]+/model/.+","/product/branchandstatustrue/[0-9]+",
                        "/producttype/name/.+","/province/[0-9]+","/role/[0-9]+","/transport/date/.+"
                        ,"/transport/targetbranch/[0-9]+","/transport/originbranch/[0-9]+/date/.+",
                        "/transport/targetbranch/[0-9]+/date/.+")
                .permitAll()

                .antMatchers()
                .hasRole("seller")

                .antMatchers()
                .hasRole("manager")

                .antMatchers( "/branchtype/create","/brand/create","/city/create","/discountrate/create",
                        "/invoicetype/create","/paymentmethod/create","/product/create","/producttype/create","/province/create",
                        "/role/create","/transport/create")
                .permitAll()

                .regexMatchers("/city/changestatus/[0-9]+",
                        "/discountrate/changestatus/[0-9]+","/invoicetype/changestatus/[0-9]+","/paymentmethod/changestatus/[0-9]+",
                        "/product/update/[0-9]+","/product/changestatus/[0-9]+","/producttype/changestatus/[0-9]+",
                        "/province/changestatus/[0-9]+","/transport/[0-9]+",
                        "/role/update/.+","/transport/originbranch/[0-9]+", "/user/ceo/.+")
                .permitAll()

                .antMatchers("/customer/create", "/invoice/create")
                .permitAll()

                .antMatchers("/ecommerce/authentication/sign-up", "/user/create","/user/all",
                        "/user/name","/user/update","/user/active")
                .permitAll()

                .regexMatchers("/user/.+","/user/changestatus/.+","/user/active/[0-9]+",
                        "/user/all/[0-9]+")
                .permitAll()

                .anyRequest().authenticated();

             http.addFilterBefore(jwtAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(){
        return new JwtAuthorizationFilter(jwtProvider);
    }
}