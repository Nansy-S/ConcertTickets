package com.prokopovich.sitairis.concerttickets.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int userId;
    private String firstName;
    private String name;
    private String patronymic;
    private String userType;
    private String login;
    private String password;
}