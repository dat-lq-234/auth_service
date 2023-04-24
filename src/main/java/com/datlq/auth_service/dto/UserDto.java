package com.datlq.auth_service.dto;

public record UserDto(
        String id,
        String email,
        String fullname
) {}
