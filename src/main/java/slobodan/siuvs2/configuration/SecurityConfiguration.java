package slobodan.siuvs2.configuration;

import slobodan.siuvs2.model.Roles;
import slobodan.siuvs2.service.SiuvsUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SiuvsUserDetailsService siuvsUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(siuvsUserDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
       //   System.out.println("postavlja se user details");
        return authenticationProvider;
    }

    @Bean
    public SiuvsUserDetailsService siuvsUserDetailsService() {
        return new SiuvsUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/php/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/forgotpassword/**").permitAll()
                .antMatchers("/publicaccess/**").permitAll()
                .antMatchers("/admin/**").hasAnyAuthority(Roles.ADMIN.toString(), Roles.RIS.toString(), Roles.MUP.toString())
                .antMatchers("/supervisor/**").hasAnyAuthority(Roles.DISTRIKT.toString(), Roles.PROVINCE.toString())
                .antMatchers("/client/**").hasAnyAuthority(Roles.CLIENT.toString(), Roles.CLIENT_READ_ONLY.toString())
                .antMatchers("/profile/**").hasAnyAuthority(Roles.ADMIN.toString(), Roles.RIS.toString(), Roles.MUP.toString(), Roles.CLIENT.toString(), Roles.CLIENT_READ_ONLY.toString(), Roles.DISTRIKT.toString(), Roles.PROVINCE.toString(),Roles.MOBILE_ONLY.toString())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/").failureUrl("/?error")
                .defaultSuccessUrl("/home")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login/?logout")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/fonts/**", "/favicon/**");
    }

}
