package com.test.task.repository;

import com.test.task.model.Customer;
import com.test.task.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findTopByCustomerOrderByCreationDateTime(Customer name);
    void deleteAllByCustomer(Customer customer);
}
