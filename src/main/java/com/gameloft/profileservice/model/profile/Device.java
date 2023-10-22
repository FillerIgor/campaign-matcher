package com.gameloft.profileservice.model.profile;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long deviceId;
    private String carrier;
    private String firmware;
    private String model;

    @ManyToOne
    @JoinColumn(name="profile_id")
    private Profile profile;
}
