package com.prokopovich.sitairis.concerttickets.dto;

import com.prokopovich.sitairis.concerttickets.entity.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewClientDto {
    private int clientId;
    private NewUserDto userInfo;
    @Pattern(regexp="[\\d]{2}[-][\\d]{3}[-][\\d]{2}[-][\\d]{2}", message="Номер телефона должен быть в формате ХХ-ХХХ-ХХ-ХХ")
    private String phone;
    @Email(message="Значение в поле Email не является адресом электронной почты.")
    private String email;
    private int ticketNumber;
}


