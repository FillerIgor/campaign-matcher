package com.gameloft.profileservice.dto.campaign;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CampaignDto {

    private String game;
    private String name;
    private Double priority;
    private MatchersDto matchers;
    private Boolean enabled;
    @JsonProperty("start_date")
    private ZonedDateTime startDate;
    @JsonProperty("end_date")
    private ZonedDateTime endDate;
    @JsonProperty("last_updated")
    private ZonedDateTime lastUpdated;

}
