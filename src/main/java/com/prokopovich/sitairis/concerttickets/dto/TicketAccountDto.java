package com.prokopovich.sitairis.concerttickets.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketAccountDto {
    private int ticketAccountId;

    private int numberFanzone;
    private double priceFanzone;
    private int numberSoldFanzone;

    private int numberDancefloor;
    private double priceDancefloor;
    private int numberSoldDancefloor;

    private int numberTribune;
    private double priceTribune;
    private int numberSoldTribune;
}
