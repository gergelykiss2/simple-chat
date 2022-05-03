package com.gergelytamas.simplechat.service;

import com.gergelytamas.simplechat.exception.MissingIdException;
import com.gergelytamas.simplechat.model.Message;
import com.gergelytamas.simplechat.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAll() {
        return this.messageRepository.findAll();
    }

    public Optional<Message> findById(final Integer id) {
        if (id == null) {
            throw new MissingIdException();
        } else {
            return this.messageRepository.findById(id);
        }
    }

    public Message save(final Message message) {
        return this.messageRepository.save(message);
    }

    public Message update(final Integer id, final Message message) {
        if (id == null) {
            throw new MissingIdException();
        } else {
            return this.messageRepository.save(message);
        }
    }

    public void delete(final Integer id) {
        if (id == null) {
            throw new MissingIdException();
        } else {
            this.messageRepository.deleteById(id);
        }
    }
}
