package com.prokopovich.sitairis.concerttickets.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewManagerDto {
    private int managerId;
    private NewUserDto userInfo;
    @Pattern(regexp="[\\d]{2}[-][\\d]{3}[-][\\d]{2}[-][\\d]{2}", message="Номер телефона должен быть в формате ХХ-ХХХ-ХХ-ХХ")
    private String phone;
    private String eventAgency;
    private int concertsNumber;
}
