package com.test.task.controller;

import com.test.task.dto.MessageDTO;
import com.test.task.dto.MessageTextDTO;
import com.test.task.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/message")
@CrossOrigin(origins = "*")
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<?> saveMessage(@RequestBody MessageDTO messageDTO) {
        Optional<List<MessageTextDTO>> response = messageService.saveMessageOrGetMessages(messageDTO);
        return response.isEmpty()? ResponseEntity.status(HttpStatus.CREATED).build(): ResponseEntity.ok(response.get());
    }
}
