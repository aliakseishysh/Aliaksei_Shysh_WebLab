package com.epam.esm.service.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadMostWidelyUsedTagDto {
    @NotEmpty(message = "Field username should not be empty")
    @Size(min = 1, max = 254, message = "Filed username should be between 1 and 254 characters")
    private String username;
}
