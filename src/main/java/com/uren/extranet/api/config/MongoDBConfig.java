package com.uren.extranet.api.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.uren.extranet.api.listener.UsersModelListener;
import com.uren.extranet.api.repository.UserRepository;

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoDBConfig {

    @Bean
    public UsersModelListener usersCascadingMongoEventListener() {
        return new UsersModelListener();
    }

}