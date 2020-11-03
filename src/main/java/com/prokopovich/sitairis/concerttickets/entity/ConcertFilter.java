package com.prokopovich.sitairis.concerttickets.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConcertFilter extends Concert {
    private String musicStyle;
    private String location;
    private String date;
    private String artistCountry;
}
