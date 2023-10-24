package inhatc.spring.shop.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin(form -> form
                .loginPage("/member/login") // 로그인 페이지
                .defaultSuccessUrl("/") // 로그인 성공 후 이동 페이지
                .failureForwardUrl("/member/login/error")
                .usernameParameter("email") // 로그인 페이지의 아이디 파라미터
                .passwordParameter("password") // 로그인 페이지의 비밀번호 파라미터
                .permitAll() //모든 사용자가 접근할 수 있음
        ); //formLogin

        http.logout(Customizer.withDefaults()); // 로그아웃 설정

        return http.authorizeHttpRequests(request -> request // 인가 정책
                .requestMatchers("/", "/member/**","/css/**").permitAll() // 루트와 /member/** 경로는 모든 사용자가 접근 가능
                .anyRequest().authenticated() // 나머진 인증된 사용자만 접근 가능
        ) //authorizeHttpRequests
                .build();
    } //filterChain

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
