package emyo.jamin.jej.crefoilo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import emyo.jamin.jej.crefoilo.entity.Role;
import emyo.jamin.jej.crefoilo.security.CustomOauth2UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOauth2UserService customOAuth2UserService;

    /**
     * url에 // 등 기호 넣어도 통과되게 한다.
     */
    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

    /**
     * url에 // 등 기호 넣어도 통과되게 한다.
     */
    @Bean
    RequestRejectedHandler requestRejectedHandler() {
        return new HttpStatusRequestRejectedHandler();
    }

    @Bean
    public WebSecurityCustomizer confiCustomizer() {
        return (web) -> web.ignoring().mvcMatchers("/css/**", "/img/**", "/js/**"); // 정적 파일 스프링 시큐리티 설정 무시
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // .csrf().disable()
                .headers().frameOptions().sameOrigin() // X-Frame-Options을 동일 오리진만 사용 가능하도록
                .and()
                .authorizeRequests() // 접근 제어 시작
                .antMatchers("/", "/portfolio/**").permitAll() // 모두 접근
                // 가능한
                // url 설정
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 이 권한을 가진 사람만 접근 가능
                .anyRequest().authenticated() // 그 외 모든 접근에 대해 로그인 필요
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("SESSION")
                .and()
                .oauth2Login() // oauth2 로그인 설정
                .defaultSuccessUrl("/myportfolio/list", true) // 로그인 성공 시 갈 url
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
        // customOAuth2UserService에서 처리를 하겠다. 그리고 "/login-success"로 이동하라.
        return http.build();
    }

}
