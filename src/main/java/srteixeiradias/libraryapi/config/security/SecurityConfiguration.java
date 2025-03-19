package srteixeiradias.libraryapi.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import srteixeiradias.libraryapi.secutiry.CustomUserDatailsService;
import srteixeiradias.libraryapi.secutiry.SocialLoginSuccessHandler;
import srteixeiradias.libraryapi.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   SocialLoginSuccessHandler successHandler) throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/usuarios").hasRole("TECNICO");
                    authorize.requestMatchers(HttpMethod.GET,"/autores/**" ).hasAnyRole("GERENTE","OPERADOR");
                    authorize.requestMatchers("/autores/**").hasRole("GERENTE");
                    authorize.requestMatchers("/livro/**").hasAnyRole("GERENTE", "OPERADOR");
                    authorize.anyRequest().authenticated();
                })
                .oauth2Login(oAuth2 -> {
                    oAuth2.successHandler(successHandler);
                })
                .build();
    }

    //@Bean
    public UserDetailsService userDetailsService(UsuarioService usuarioService){
       return new CustomUserDatailsService(usuarioService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }
}
