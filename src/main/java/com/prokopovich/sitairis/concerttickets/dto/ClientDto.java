package com.prokopovich.sitairis.concerttickets.dto;

import com.prokopovich.sitairis.concerttickets.entity.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDto {
    private int clientId;
    private User userInfo;
    private String phone;
    private String email;
    private int ticketNumber;
}
