package com.prokopovich.sitairis.concerttickets.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table(name = "билет")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_билета")
    private int ticketId;
    @Column(name = "id_концерта")
    private int concertId;
    @ManyToOne
    @JoinColumn(name = "id_концерта", insertable = false, updatable = false)
    private Concert concertInfo;
    @Column(name = "id_пользователя")
    private int clientId;
    @ManyToOne
    @JoinColumn(name = "id_пользователя", insertable = false, updatable = false)
    private Client clientInfo;

    @Column(name = "тип_билета")
    private String typeTicket;
    @Column(name = "цена_билета")
    private double priceTicket;
}
