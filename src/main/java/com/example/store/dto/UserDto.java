package com.example.store.dto;

import com.example.store.dto.validation.OnCreate;
import com.example.store.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDto {
    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;
    @NotNull(message = "Username must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Username length must be less than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String username;
    @NotNull(message = "First name must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "First name length must be less than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String firstname;
    @NotNull(message = "Last name must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Last name length must be less than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String lastname;
    @NotNull(message = "email must be not null", groups = {OnCreate.class, OnUpdate.class})
    private String email;
}
