package com.bookcode;

import com.bookcode.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@SpringBootApplication
public class Jdbch2Application implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Jdbch2Application.class);
    public static void main(String[] args) {SpringApplication.run(Jdbch2Application.class, args);}
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void run(String... strings)throws Exception{
        log.info("Creating tables");
        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customers(" + "id SERIAL,first_name VARCHAR(255),last_name VARCHAR(255))");
        List<Object[]> splitUpNames = Arrays.asList("John Woo","Jeff Dean","Josh Bloch","Josh Long").stream().map(name -> name.split(" ")).collect(Collectors.toList());
        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s",name[0],name[1])));
        jdbcTemplate.batchUpdate("INSERT INFO customers(first_name, last_name) VALUE (?,?)",splitUpNames);
    log.info("Querying for customer records where first_name = 'Josh':");
    jdbcTemplate.query(
            "SELECT id,first_name,last_name FROM customers WHERE first_name = ?",
            new Object[]{"Josh"},
            (rs, rowNum) -> new Customer(rs.getLong("id"),rs.getString("first_name"),rs.getString("last_name"))
    ).forEach(customer -> log.info(customer.toString()));
    }

}
