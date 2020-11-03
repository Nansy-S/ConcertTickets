package com.prokopovich.sitairis.concerttickets.dto;

import lombok.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewUserDto {
    @NotNull(message="{valid.userId.notNull}")
    private int userId;
    @NotBlank(message="Поле Фамилия не заполнено.")
    @Pattern(regexp="[A-Za-zА-Яа-я]{3,}", message="Фамилия может содержать только буквы")
    private String firstName;
    @NotBlank(message="Поле не заполнено.")
    @Pattern(regexp="[A-Za-zА-Яа-я]{3,}", message="Имя может содержать только буквы")
    private String name;
    @NotBlank(message="Поле не заполнено.")
    @Pattern(regexp="[A-Za-zА-Яа-я]{3,}", message="Отчество может содержать только буквы")
    private String patronymic;
    private String userType;
    @NotBlank(message="Поле не заполнено.")
    @Pattern(regexp="[^А-Яа-я]{3,}", message="Не может содержать буквы русского алфавита")
    private String login;
    @NotBlank(message="Поле не заполнено.")
    @Pattern(regexp="[A-Za-z0-9]{4,}", message="Только цифры и буквы латинского алфавита и больше 4 символов")
    private String password;
}
