//package ru.gb.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import ru.gb.user.service.UserDetailService;
//
//import java.util.Arrays;
//
//@EnableWebSecurity
//public class SecurityConfiguration  {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(it -> it.anyRequest().permitAll())
//                .build();
//    }
////        http.csrf(AbstractHttpConfigurer::disable)
////                .cors(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(request-> request
////                .requestMatchers("/user", "/css/**", "/img/**", "/error", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
////                .anyRequest().hasAnyRole("USER", "ADMIN"))
////                .and()
////                .formLogin()
////                .loginProcessingUrl("/session")
////                .successHandler(successHandler())
////                .failureHandler(failureHandler())
////                .and()
////                .exceptionHandling()
////                .authenticationEntryPoint(authenticationEntryPoint())
////                .and()
////                .logout()
////                .logoutUrl("/logout")
////                .logoutSuccessUrl("/session")
////                .and()
////                .httpBasic();
////
////        return http.build();
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService)
//            throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailService)
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();
//    }
//
////    @Bean
////    CorsConfigurationSource corsConfigurationSource() {
////        CorsConfiguration configuration = new CorsConfiguration();
//////        configuration.setAllowedOrigins(Arrays.asList("http://localhost:63342/"));
////        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", configuration);
////        return source;
////    }
//
//    private AuthenticationSuccessHandler successHandler() {
//        return (httpServletRequest, httpServletResponse, authentication) -> {
//            httpServletResponse.getWriter().append("OK");
//            httpServletResponse.setStatus(200);
//        };
//    }
//
//    private AuthenticationFailureHandler failureHandler() {
//        return (httpServletRequest, httpServletResponse, e) -> {
//            httpServletResponse.getWriter().append("Authentication failure");
//            httpServletResponse.setStatus(401);
//        };
//    }
//
//    private AuthenticationEntryPoint authenticationEntryPoint() {
//        return (httpServletRequest, httpServletResponse, e) -> {
//            httpServletResponse.getWriter().append("Not authenticated");
//            httpServletResponse.setStatus(401);
//        };
//    }
//}
