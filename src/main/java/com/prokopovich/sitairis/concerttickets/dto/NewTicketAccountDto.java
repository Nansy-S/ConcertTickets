package com.prokopovich.sitairis.concerttickets.dto;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewTicketAccountDto {
    private int ticketAccountId;

    @Digits(integer=8, fraction=0, message="Некорректные данные")
    private int numberFanzone;
    @Digits(integer=8, fraction=2, message="Некорректные данные")
    private double priceFanzone;
    private int numberSoldFanzone;

    @Digits(integer=8, fraction=0, message="Некорректные данные")
    private int numberDancefloor;
    @Digits(integer=8, fraction=2, message="Некорректные данные")
    private double priceDancefloor;
    private int numberSoldDancefloor;

    @Digits(integer=8, fraction=0, message="Некорректные данные")
    private int numberTribune;
    @Digits(integer=8, fraction=2, message="Некорректные данные")
    private double priceTribune;
    private int numberSoldTribune;
}
