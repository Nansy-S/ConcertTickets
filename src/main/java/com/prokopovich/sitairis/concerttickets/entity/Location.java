package com.prokopovich.sitairis.concerttickets.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "место_проведения")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_места_проведения")
    private int locationId;
    @Column(name="название")
    private String locationName;
    @Column(name = "адрес")
    private String address;
}
