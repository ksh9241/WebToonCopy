package project.toy.webtoon_copy.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project.toy.webtoon_copy.user.UserService;

@EnableWebSecurity
@Configuration
@Data
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/swagger-ui.*", "/resources/**").permitAll()
//                .antMatchers("/").hasAuthority("ROLE_ADMIN")
//                .antMatchers("/cookie/**").hasRole("ADMIN") // hasRole() == "ROLE_" 가 접두어로 붙기 때문에 빼고 작성한다.
                .antMatchers("/cookie/**").access("hasRole('USER') or hasRole('ADMIN')") // hasRole() == "ROLE_" 가 접두어로 붙기 때문에 빼고 작성한다.
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
}
