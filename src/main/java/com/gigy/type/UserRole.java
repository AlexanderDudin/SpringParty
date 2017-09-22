package com.gigy.type;

public enum UserRole {

    ADMIN("Admin user"),
    VALIDATED_USER("Validated user");

    private String description;

    UserRole(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }
}
