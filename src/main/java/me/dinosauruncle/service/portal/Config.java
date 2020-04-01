package me.dinosauruncle.service.portal;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableJpaRepositories(basePackages = "me.dinosauruncle.msa.account")
@Configuration
public class Config {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ProxyFactoryBean proxyFactoryBean() { return new ProxyFactoryBean();}
}
