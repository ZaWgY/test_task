package com.test.task;

import com.test.task.dto.MessageDTO;
import com.test.task.model.Customer;
import com.test.task.model.Message;
import com.test.task.repository.CustomerRepository;
import com.test.task.repository.MessageRepository;
import com.test.task.service.MessageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MessageServiceTests {

	@Autowired
	private MessageService messageService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MessageRepository messageRepository;

	private final String TEST_CUSTOMER_NAME = "test_customer";

	@BeforeEach
	void init() {
		Customer customer = new Customer();
		customer.setName(TEST_CUSTOMER_NAME);
		customer.setPassword("123456");
		customerRepository.save(customer);

		for (int i = 0; i < 12; i++) {
			Message message = new Message();
			message.setCustomer(customer);
			message.setText("It's a message #" + i+1);
			messageRepository.save(message);
		}
	}

	@AfterEach
	void clear() {
		Customer customer = customerRepository.findCustomerByName(TEST_CUSTOMER_NAME).get();
		messageRepository.deleteAllByCustomer(customer);
		customerRepository.delete(customer);
	}


	@Test
	void testHistory10Messages(){
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setMessage("history 10");
		messageDTO.setName(TEST_CUSTOMER_NAME);
		assertThat(messageService.saveMessageOrGetMessages(messageDTO).get()).hasSize(10) ;
	}

}
