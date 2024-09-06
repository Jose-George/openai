package com.openai.common.api.domain.service;

import com.openai.common.api.domain.exception.EntityNotFoundException;
import com.openai.common.api.domain.model.User;
import com.openai.common.api.domain.repository.UserRepository;
import com.openai.common.api.domain.repository.filter.UserFilter;
import com.openai.common.api.infrastructure.UserSpecs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> findUsers(Pageable pageable, UserFilter userFilter) {
        return userRepository.findAll(UserSpecs.filter(userFilter), pageable);
    }

    @Transactional
    public void active(Long id) {
        var user = seekOrFail(id);
        user.active();
    }

    @Transactional
    public void deactivate(Long id) {
        var user = seekOrFail(id);
        user.deactivate();
    }

    private User seekOrFail(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));
    }

}
