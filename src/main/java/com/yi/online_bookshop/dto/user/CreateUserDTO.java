package com.yi.online_bookshop.dto.user;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserDTO {

    @NotEmpty(message = "Name is required")
    private String name;

    @Email(message = "Email should be valid", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotNull(message = "Age is required")
    @Min(0)
    @Max(150)
    private Integer age;

    @NotNull(message = "ActiveFrom is required in format YYYY-MM-DD")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime activeFrom;
}
