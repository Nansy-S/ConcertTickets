package com.prokopovich.sitairis.concerttickets.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table(name = "учет_билетов")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketAccount {
    @Id
    @Column(name = "id_учет_билетов")
    private int ticketAccountId;

    @Column(name = "количество_фанзона")
    private int numberFanzone;
    @Column(name = "цена_фанзона")
    private double priceFanzone;
    @Column(name = "количество_купленных_фанзона")
    private int numberSoldFanzone;

    @Column(name = "количество_танцпол")
    private int numberDancefloor;
    @Column(name = "цена_танцпол")
    private double priceDancefloor;
    @Column(name = "количество_купленных_танцпол")
    private int numberSoldDancefloor;

    @Column(name = "количество_трибуны")
    private int numberTribune;
    @Column(name = "цена_трибуны")
    private double priceTribune;
    @Column(name = "количество_купленных_трибуны")
    private int numberSoldTribune;
}
