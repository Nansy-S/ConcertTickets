package com.prokopovich.sitairis.concerttickets.entity;

import lombok.*;
import javax.persistence.*;


@Data
@Entity
@Table(name = "концерт")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_концерта")
    private int concertId;
    @Column(name = "дата_концерта")
    private String concertDate;
    @Column(name = "время_концерта")
    private String concertTime;
    @Column(name = "афиша")
    private String nameFilePoster;

    @Column(name = "id_артиста")
    private int artistId;
    @ManyToOne
    @JoinColumn(name = "id_артиста", insertable = false, updatable = false)
    private Artist artistInfo;

    @Column(name = "id_места_проведения")
    private int locationId;
    @ManyToOne
    @JoinColumn(name = "id_места_проведения", insertable = false, updatable = false)
    private Location placeInfo;

    @Column(name = "id_пользователя")
    private int managerId;
    @ManyToOne
    @JoinColumn(name = "id_пользователя", insertable = false, updatable = false)
    private Manager managerInfo;

    @Column(name = "id_учет_билетов")
    private int ticketAccountId;
    @ManyToOne
    @JoinColumn(name = "id_учет_билетов", insertable = false, updatable = false)
    private TicketAccount ticketAccountInfo;
}
