package com.gergelytamas.simplechat.service;

import com.gergelytamas.simplechat.exception.MissingIdException;
import com.gergelytamas.simplechat.model.User;
import com.gergelytamas.simplechat.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public Optional<User> findById(final Integer id) {
        if (id == null) {
            throw new MissingIdException();
        } else {
            return this.userRepository.findById(id);
        }
    }

    public User save(final User user) {
        return this.userRepository.save(user);
    }

    public User update(final Integer id, final User user) {
        if (id == null) {
            throw new MissingIdException();
        } else {
            return this.userRepository.save(user);
        }
    }

    public void delete(final Integer id) {
        if (id == null) {
            throw new MissingIdException();
        } else {
            this.userRepository.deleteById(id);
        }
    }
}
