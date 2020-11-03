package com.prokopovich.sitairis.concerttickets.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewLocationDto {
    private int locationId;
    @NotBlank(message="Поле не заполнено.")
    private String locationName;
    @NotBlank(message="Поле не заполнено.")
    private String address;
}
