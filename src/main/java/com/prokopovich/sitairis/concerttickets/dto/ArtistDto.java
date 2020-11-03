package com.prokopovich.sitairis.concerttickets.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArtistDto {
    private int artistId;
    private String name;
    private String musicStyle;
    private String country;
    private int foundationYear;
}
