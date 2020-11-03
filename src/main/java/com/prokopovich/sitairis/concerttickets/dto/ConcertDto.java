package com.prokopovich.sitairis.concerttickets.dto;

import com.prokopovich.sitairis.concerttickets.entity.Artist;
import com.prokopovich.sitairis.concerttickets.entity.Location;
import com.prokopovich.sitairis.concerttickets.entity.Manager;
import com.prokopovich.sitairis.concerttickets.entity.TicketAccount;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConcertDto {
    private int concertId;
    private String concertDate;
    private String concertTime;
    private String nameFilePoster;

    private int artistId;
    private Artist artistInfo;

    private int locationId;
    private Location placeInfo;

    private int managerId;
    private Manager managerInfo;

    private int ticketAccountId;
    private TicketAccount ticketAccountInfo;
}
