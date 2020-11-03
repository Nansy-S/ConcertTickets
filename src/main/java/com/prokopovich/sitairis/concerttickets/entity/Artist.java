package com.prokopovich.sitairis.concerttickets.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table(name = "артист")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_артиста")
    private int artistId;
    @Column(name="название")
    private String name;
    @Column(name = "стиль_музыки")
    private String musicStyle;
    @Column(name = "страна")
    private String country;
    @Column(name = "год_основания")
    private int foundationYear;
}