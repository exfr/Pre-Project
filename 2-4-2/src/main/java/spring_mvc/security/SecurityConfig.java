package spring_mvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService; // service get user


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());  // config for authentication
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")  //Страница с формой
                .successHandler(new LoginSuccessHandler())
                .loginProcessingUrl("/login")   //url формы
                .usernameParameter("username")  //Параметры формы
                .passwordParameter("password")  //Параметры формы
                .permitAll();

        http
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .and().csrf().disable();  //разрыв соединения и инфорамации о пользователе

        http
                .authorizeRequests()
                .antMatchers("/login").anonymous() //Незалогиненный пользователь может посещать только страницу /login
                .antMatchers("/admin", "/admin/**")
                .access("hasAuthority('ROLE_ADMIN')")//ADMIN может посещать страницы /admin/**
                .antMatchers("/hello")
                .access("hasAuthority('ROLE_USER')");//USER посещает страницу /hello
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();  //Кодировка пароля
    }
}