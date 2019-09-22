package me.dinosauruncle.msa.account;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "me.dinosauruncle.msa.account")
@Configuration
public class Config {
}
