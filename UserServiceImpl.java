package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User createUser(User user) {
        // you can add checks like unique email here
        return repo.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setName(user.getName());
                    existing.setEmail(user.getEmail());
                    return repo.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Override
    public void deleteUser(Long id) {
        repo.deleteById(id);
    }
}
