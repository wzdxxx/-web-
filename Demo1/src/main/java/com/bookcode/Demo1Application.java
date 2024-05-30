package com.bookcode;

import com.bookcode.entity.User;
import com.bookcode.dao.UserCrudRepository;
import com.bookcode.dao.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class Demo1Application {
    private static final Logger log = LoggerFactory.getLogger(Demo1Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            repository.save(new User("a", "a"));
            repository.save(new User("b", "b"));
            repository.save(new User("c", "c"));
            repository.save(new User("d", "d"));


            log.info("User found with findLastName('a') :");
            log.info("------------------------------");

            for (Object user : repository.findAll()) {
                log.info(user.toString());
            }
            repository.findById(1L).ifPresent(User -> {
                log.info("User found with findById(1L) : ");
                log.info("----------------------------------");
                log.info(User.toString());
            });
        };

    }
}