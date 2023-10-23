package com.example.task_15.message.controller;


import com.example.task_15.message.model.Message;
import com.example.task_15.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Get requests
    @GetMapping("/messages")
    public Iterable<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/message/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return messageService.getMessageById(id);
    }



    // Post requests
    @PostMapping("/messages")
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        return new ResponseEntity<>(messageService.addMessage(message), HttpStatus.CREATED);
    }


    // Put requests
    @PutMapping("/messages/{id}")
    public Optional<Message> updateMessageById(@PathVariable int id, @RequestBody Message message) {
        return messageService.updateMessageById(id, message);
    }


    // Delete requests
    @DeleteMapping("/messages/{id}")
    public void deleteMessageById(@PathVariable int id) {
        messageService.deleteMessageById(id);
    }
}
