package com.epam.esm.service.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteTagByIdDto {
    @NotNull(message = "Field id should not be empty")
    @Min(value = 1, message = "Filed id should be equal or greater than 1")
    private Long id;
}
