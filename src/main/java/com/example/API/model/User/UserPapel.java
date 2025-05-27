package com.example.API.model.User;

public enum UserPapel {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String role;

    UserPapel(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
