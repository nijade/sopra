package com.example.sopra.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
@EnableWebSecurity
@EnableScheduling
public class SecurityConfiguration implements WebMvcConfigurer {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        // define all URLs which should be accessible without login
                        auth -> auth
                                .requestMatchers("/register", "/login", "/","/searchPlants/**", "searchPlantsPriceAscending", "searchPlantsAdditionalFilters").permitAll()
                                // define all URLs which require an authenticated user with a certain role
                                // NOTE: Spring Security automatically adds "ROLE_" while performing this check. For this reason we do not
                                // have to use "ROLE_ADMIN" here, which we define in the TestDatabaseLoader.
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                // all other URLs (except the ones above) require authentication too
                                .requestMatchers("/profile/**").authenticated()
                                .anyRequest().authenticated())
                // include CSRF token, which may be required while performing AJAX-requests
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers("/console/**"))
                // define the login-page, which is also accessible for everyone

                .formLogin( formLogin -> formLogin
                        .loginPage("/login").failureUrl("/login?error=true").permitAll()
                        .defaultSuccessUrl("/", true)
                        .usernameParameter("username")
                        .passwordParameter("password"))
                // everyone may logout

                .logout(logout -> logout.permitAll()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout"))

                //Disables header security. This allows the use of the h2 console.
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                // gewähre immer Zugriff auf Dateien in den folgenden Ordnern
                .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    /**
     * Password-encoder Bean der Spring Security. Wird zum Verschlüsseln von Passwörtern benötigt.
     *
     * @return BCryptPasswordEncoder bean.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LocaleResolver localeResolver() {
        var resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.GERMAN);
        return resolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}