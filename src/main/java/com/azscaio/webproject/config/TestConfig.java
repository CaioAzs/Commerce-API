package com.azscaio.webproject.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.azscaio.webproject.models.Order;
import com.azscaio.webproject.models.User;
import com.azscaio.webproject.repositories.OrderRepository;
import com.azscaio.webproject.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Caio de Souza", "souzascaio23cs@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Jonas Brothers", "jonas@gmail.com", "977777777", "123456");

        Order o1 = new Order(null, Instant.parse("2020-06-20T19:53:08Z"), u1);
        Order o2 = new Order(null, Instant.parse("2021-07-20T19:53:08Z"), u2);
        Order o3 = new Order(null, Instant.parse("2023-01-20T19:53:08Z"), u1);

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1,o2,o3));

    }   

}