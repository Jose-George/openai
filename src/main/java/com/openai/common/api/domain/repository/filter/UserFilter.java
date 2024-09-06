package com.openai.common.api.domain.repository.filter;

public class UserFilter {

    private boolean isActive;
    private String name;

    public UserFilter(boolean isActive, String name) {
        this.isActive = isActive;
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
