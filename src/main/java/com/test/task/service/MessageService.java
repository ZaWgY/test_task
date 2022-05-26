package com.test.task.service;

import com.test.task.dto.MessageDTO;
import com.test.task.dto.MessageTextDTO;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    /**
     * Метод сохраняет сообщение.
     * В случае если приходит сообщение вида "history N",
     * то возвращает N последних сообщений пользователя.
     */
    Optional<List<MessageTextDTO>> saveMessageOrGetMessages(MessageDTO messageDTO);
}
