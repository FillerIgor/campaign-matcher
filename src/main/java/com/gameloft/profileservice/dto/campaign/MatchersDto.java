package com.gameloft.profileservice.dto.campaign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MatchersDto {

    @JsonProperty("does_not_have")
    private DoesNotHaveDto doesNotHave;
    private HasDto has;
    private LevelDto level;

}
