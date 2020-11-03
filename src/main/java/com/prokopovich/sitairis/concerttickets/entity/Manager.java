package com.prokopovich.sitairis.concerttickets.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "менеджер")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Manager implements Serializable {
    @Id
    @Column(name = "id_пользователя")
    private int managerId;
    @ManyToOne
    @JoinColumn(name = "id_пользователя", insertable = false, updatable = false)
    private User userInfo;
    @Column(name="номер_телефона")
    private String phone;
    @Column(name = "event_агенство")
    private String eventAgency;
    @Column(name = "количество_концертов")
    private int concertsNumber;
}
