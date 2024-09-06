package com.openai.admin.api.controller;

import com.openai.admin.api.model.UserAdminResponse;
import com.openai.common.api.domain.model.User;
import com.openai.common.api.domain.repository.filter.UserFilter;
import com.openai.common.api.domain.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class UserAdminController {

    private final UserService userService;

    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public Page<UserAdminResponse> findAllUsers(Pageable pageable, UserFilter userFilter) {

        Page<User> usersPage = userService.findUsers(pageable, userFilter);

        List<UserAdminResponse> usersResponse = UserAdminResponse.mapToUser(usersPage.getContent());

        return new PageImpl<>(usersResponse, pageable, usersPage.getTotalElements());
    }

    @PutMapping("/{userId}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void active(@PathVariable Long userId) {
        userService.active(userId);
    }

    @DeleteMapping("/{userId}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactive(@PathVariable Long userId) {
        userService.deactivate(userId);
    }


}
