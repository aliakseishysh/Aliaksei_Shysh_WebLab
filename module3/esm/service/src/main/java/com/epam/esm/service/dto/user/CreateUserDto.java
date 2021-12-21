package com.epam.esm.service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    @NotEmpty(message = "Field username should not be empty")
    @Size(min = 1, max = 254, message = "Filed username should be between 1 and 254 characters")
    private String username;
    @NotEmpty(message = "Field password should not be empty")
    @Size(min = 1, max = 254, message = "Filed password should be between 1 and 254 characters")
    private String password;
}
