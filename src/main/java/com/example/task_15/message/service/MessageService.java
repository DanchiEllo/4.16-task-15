package com.example.task_15.message.service;

import com.example.task_15.message.model.Message;
import com.example.task_15.message.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Iterable<Message> getAllMessages() {
        return repository.findAll();
    }

    public Optional<Message> getMessageById(int id) {
        return repository.findById(id);
    }

    public Message addMessage(Message message) {
        if (message.getTime() == null) {
            message.setTime(LocalDateTime.now());
        }
        return repository.save(message);
    }

    public Optional<Message> updateMessageById(int id, Message message) {
        Optional<Message> optionalMessage = repository.findById(id);

        if (optionalMessage.isPresent()) {
            Message updatedMessage = optionalMessage.get();

            updatedMessage.setTitle(message.getTitle());
            updatedMessage.setText(message.getText());
            updatedMessage.setTime(message.getTime());

            return Optional.of(repository.save(updatedMessage));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public void deleteMessageById(int id) {
        repository.deleteById(id);
    }

}
