package com.ead.authuser.service.impl;

import com.ead.authuser.model.UserModel;
import com.ead.authuser.repository.UserRepository;
import com.ead.authuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public List<UserModel> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<UserModel> findById(UUID userId) {
        return repository.findById(userId);
    }

    @Override
    public void delete(UserModel userModel) {
        repository.delete(userModel);
    }

    @Override
    public void save(UserModel userModel) {
        repository.save(userModel);
    }

    @Override
    public boolean existsByUsername(String userName) {
        return repository.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Page<UserModel> findAll(Specification<UserModel> spec,Pageable paginacao) {
        return repository.findAll(spec, paginacao);
    }


}
