package com.test.task.init;

import com.test.task.model.Customer;
import com.test.task.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Загрузка исходных данных в БД
 */

@Component
@RequiredArgsConstructor
public class DataInit implements ApplicationRunner {
    @Autowired
    private final CustomerRepository customerRepository;

    @Override
    public void run(ApplicationArguments args) {
        Customer customer = new Customer();
        customer.setName("user");
        // Пароль - 123
        customer.setPassword("$2a$10$ynNXbCI7tyVlxkktogmpVO4ktIGu/185P8T9wYjIFmO8uKlR3JQLW");
        customerRepository.save(customer);
    }
}
