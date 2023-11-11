package com.example.bookservice.query.rest;

import com.example.bookservice.query.rest.model.BookRestModel;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BooksQueryController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/getBook")
    public ArrayList getBooks() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Object book = rabbitTemplate.convertSendAndReceive("Direct", "getBook", "");
        return (ArrayList) book;
    }

    @GetMapping(value = "/getBook/{bookId}")
    public BookRestModel getBookByBookId(@PathVariable("bookId") String bookId) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Object book = rabbitTemplate.convertSendAndReceive("Direct", "getBookId", bookId);
        return (BookRestModel) book;
    }
}
