package com.gameloft.profileservice.dto.profile;

import lombok.Data;

@Data
public class DeviceDto {
    private String carrier;
    private String firmware;
    private Long id;
    private String model;
}
