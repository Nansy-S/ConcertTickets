package com.prokopovich.sitairis.concerttickets.dto;

import com.prokopovich.sitairis.concerttickets.entity.Client;
import com.prokopovich.sitairis.concerttickets.entity.Concert;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketDto {
    private int ticketId;

    private int concertId;
    private Concert concertInfo;
    private int clientId;
    private Client clientInfo;

    private String typeTicket;
    private double priceTicket;
}
