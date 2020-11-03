package com.prokopovich.sitairis.concerttickets.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "покупатель_билета")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @Column(name = "id_пользователя")
    private int clientId;
    @ManyToOne
    @JoinColumn(name = "id_пользователя", insertable = false, updatable = false)
    private User userInfo;
    @Column(name="номер_телефона")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "количество_билетов")
    private int ticketNumber;
}
