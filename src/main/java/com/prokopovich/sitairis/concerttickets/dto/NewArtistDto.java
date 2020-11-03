package com.prokopovich.sitairis.concerttickets.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewArtistDto {
    private int artistId;
    @NotBlank(message="Поле Название не заполнено.")
    private String name;
    private String musicStyle;
    @NotBlank(message="Поле Название не заполнено.")
    private String country;
    @DecimalMin(value="1950", inclusive=true, message="Год не может быть меньше 1950.")
    @DecimalMax(value="2020", message="Год не может быть больше 2020.")
    private int foundationYear;
}
