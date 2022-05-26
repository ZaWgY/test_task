package com.test.task.serviceimpl;

import com.test.task.dto.MessageDTO;
import com.test.task.dto.MessageTextDTO;
import com.test.task.model.Customer;
import com.test.task.model.Message;
import com.test.task.repository.CustomerRepository;
import com.test.task.repository.MessageRepository;
import com.test.task.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private EntityManager entityManager;
    private MessageRepository messageRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, CustomerRepository customerRepository, EntityManager entityManager) {
        this.messageRepository = messageRepository;
        this.customerRepository = customerRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Optional<List<MessageTextDTO>> saveMessageOrGetMessages(MessageDTO messageDTO) {

        if(messageDTO.getName() == null || messageDTO.getMessage() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Customer customer = customerRepository.findCustomerByName(messageDTO.getName()).orElseThrow(()->
            new ResponseStatusException(HttpStatus.BAD_REQUEST)
        );

        if(Pattern.compile("history +\\d+").matcher(messageDTO.getMessage()).find()) {
            Integer numberOfMessages = Integer.valueOf(messageDTO.getMessage().split(" +")[1]);
            return Optional.of( getLastMessages(customer,numberOfMessages).stream()
                    .map(
                            (m) -> new MessageTextDTO(m.getText()))
                    .collect(
                            Collectors.toList()
                    )
            );
        } else {
            Message message = new Message();
            message.setText(messageDTO.getMessage());
            message.setCustomer(customer);
            messageRepository.save(message);
        }
        return Optional.empty();
    }

    private List<Message> getLastMessages(Customer customer, int count) {
        return entityManager
                .createQuery("SELECT m FROM Message m WHERE m.customer = :customer ORDER BY m.creationDateTime DESC")
                .setParameter("customer", customer)
                .setMaxResults(count)
                .getResultList();

    }
}
