package com.healthcare.babysoft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${cors.origins}")
    private String corsOrigins;

    @Autowired
    private Environment env;

    @Autowired
    private JwtTokenStore tokenStore;



//    private static final String[] NIVEL_1 = {"/maes", "/maes/{cpf}"};
//    private static final String[] NIVEL_1_2_3_ALL  = {"/maes/**"};

    private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**" };
    private static final String[] NIVEL_1_2_3 = {"/medicos/**", "/enfermeiros/**", "/recemnascidos/**", "/especialidades/**", "/equipesparto/**", "/partos/**"};
    private static final String[] NIVEL_1_2_3_MAES = {"/maes", "/maes/{cpf}"};
    private static final String[] NIVEL_2_3 = {"/maes/**", "/recemnascidos/**", "/partos/**"};
    private static final String[] NIVEL_3 = {"/funcionarios/**", "/medicos/**", "/enfermeiros/**", "/recemnascidos/**", "/especialidades/**", "/equipesparto/**"};

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        // H2
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET, NIVEL_1_2_3).hasAnyRole("NIVEL_1", "NIVEL_2", "NIVEL_3")
                .antMatchers(NIVEL_1_2_3_MAES).hasAnyRole("NIVEL_1", "NIVEL_2", "NIVEL_3")
                .antMatchers(NIVEL_2_3).hasAnyRole("NIVEL_2", "NIVEL_3")
                .antMatchers(NIVEL_3).hasRole("NIVEL_3")
                .anyRequest().authenticated();

        http.cors().configurationSource(corsConfigurationSource());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        String[] origins = corsOrigins.split(",");

        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOriginPatterns(Arrays.asList(origins));
        corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
        corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean
                = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
