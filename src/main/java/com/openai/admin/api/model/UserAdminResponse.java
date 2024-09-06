package com.openai.admin.api.model;

import com.openai.common.api.domain.model.User;

import java.util.List;

public record UserAdminResponse(String name, boolean active, Long id, String occupation, String email) {

    public static List<UserAdminResponse> mapToUser(List<User> users) {
        return users.stream()
                .map(user -> map(user))
                .toList();
    }

    private static UserAdminResponse map(User user) {
        return new UserAdminResponse(user.getName(), user.isActive(), user.getId(), user.getOccupation(), user.getEmail());
    }
}
