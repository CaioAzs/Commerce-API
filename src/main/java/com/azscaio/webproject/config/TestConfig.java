package com.azscaio.webproject.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.azscaio.webproject.models.Category;
import com.azscaio.webproject.models.Order;
import com.azscaio.webproject.models.Payment;
import com.azscaio.webproject.models.Product;
import com.azscaio.webproject.models.User;
import com.azscaio.webproject.models.enums.OrderStatus;
import com.azscaio.webproject.repositories.CategoryRepository;
import com.azscaio.webproject.repositories.OrderRepository;
import com.azscaio.webproject.repositories.ProductRepository;
import com.azscaio.webproject.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        Category c1 = new Category(null, "Electronics");
        Category c2 = new Category(null, "Cleaning");
        Category c3 = new Category(null, "Books");

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));

        p1.getCategories().add(c3);
        p2.getCategories().add(c1);
        p3.getCategories().add(c1);
        p4.getCategories().add(c1);
        p5.getCategories().add(c3);

        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));

        User u1 = new User(null, "Caio de Souza", "souzascaio23cs@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Jonas Brothers", "jonas@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(u1, u2));

        Order o1 = new Order(null, Instant.parse("2020-06-20T19:53:08Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2021-07-20T19:53:08Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2023-01-20T19:53:08Z"), OrderStatus.CANCELED, u1);

        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        Payment pay1 = new Payment(null, Instant.parse("2020-06-20T20:53:08Z"), o1);
        o1.setPayment(pay1);

        orderRepository.save(o1);
    }
}
