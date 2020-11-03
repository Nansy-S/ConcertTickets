package com.prokopovich.sitairis.concerttickets.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ManagerDto {
    private int managerId;
    private UserDto userInfo;
    private String phone;
    private String eventAgency;
    private int concertsNumber;
}