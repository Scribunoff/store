package com.example.store.payload.request;

import com.example.store.dto.validation.OnCreate;
import com.example.store.dto.validation.OnUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SignupRequest {
    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;
    @NotEmpty(message = "Username must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Username length must be less than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String username;
    @NotEmpty(message = "First name must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "First name length must be less than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String firstname;
    @NotEmpty(message = "Last name must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Size(max = 255, message = "Last name length must be less than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String lastname;
    @Email(message = "It should have email format")
    @NotEmpty(message = "email must be not null", groups = {OnCreate.class, OnUpdate.class})
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(message = "Password must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 6, message = "Password must be at least 6 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(message = "Password confirmation must be not null")
    private String confirmationPassword;

}
