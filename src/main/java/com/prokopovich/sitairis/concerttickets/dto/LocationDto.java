package com.prokopovich.sitairis.concerttickets.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocationDto {
    private int locationId;
    private String locationName;
    private String address;
}
