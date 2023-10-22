package com.gameloft.profileservice.model.profile;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clan")
public class Clan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long clanId;
    private String name;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
