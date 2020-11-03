package com.prokopovich.sitairis.concerttickets.dto;

import com.prokopovich.sitairis.concerttickets.entity.Artist;
import com.prokopovich.sitairis.concerttickets.entity.Location;
import com.prokopovich.sitairis.concerttickets.entity.Manager;
import com.prokopovich.sitairis.concerttickets.entity.TicketAccount;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewConcertDto {
    private int concertId;
    @NotBlank(message="Поле не заполнено.")
    private String concertDate;
    @NotBlank(message="Поле не заполнено.")
    private String concertTime;
    private String nameFilePoster;

    private int artistId;
    private Artist artistInfo;

    private int locationId;
    private Location placeInfo;

    private int managerId;
    private Manager managerInfo;

    private int ticketAccountId;
    private NewTicketAccountDto ticketAccountInfo;
}
