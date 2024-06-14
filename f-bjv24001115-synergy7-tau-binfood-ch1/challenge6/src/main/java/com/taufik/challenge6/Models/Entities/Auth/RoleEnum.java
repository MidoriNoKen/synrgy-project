package com.taufik.challenge6.Models.Entities.Auth;

public enum RoleEnum {
    CUSTOMER,
    SELLER;

    public static RoleEnum toRoleEnum(String roleString) {
        try {
            return RoleEnum.valueOf(roleString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + roleString);
        }
    }
}
