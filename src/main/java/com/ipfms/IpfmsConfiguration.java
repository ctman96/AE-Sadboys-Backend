package com.ipfms;

import com.ipfms.filters.IdentityFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;

/**
 * Spring Configuration for the application
 */
@Configuration
public class IpfmsConfiguration {

    /**
     * Sets allowed CORS origin based on 'CORS' environment variable
     *
     * @return CorsFilter bean
     */
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        //Load from environment
        String corsUrl = System.getenv().get("CORS");
        if (corsUrl != null){
            config.addAllowedOrigin(corsUrl);
        }
        config.addAllowedOrigin("http://localhost:4200");

        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        CorsFilter bean = new CorsFilter(source);
        return bean;
    }

    /**
     * Registers the IdentityFilter
     * @return identity filter registration bean
     */
    @Bean
    public FilterRegistrationBean identityFilterRegistration(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(identityFilter());
        registration.setName("identityFilter");
        return registration;
    }
    public Filter identityFilter(){
        return new IdentityFilter();
    }


}
