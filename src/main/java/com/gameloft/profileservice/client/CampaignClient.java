package com.gameloft.profileservice.client;

import com.gameloft.profileservice.dto.campaign.CampaignDto;
import com.gameloft.profileservice.exception.CampaignException;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class CampaignClient {

    public Collection<CampaignDto> retrieveCurrentCampaign() {
        //todo add Wiremock
        throw new CampaignException("Unable to retrieve response from Campaign Service",
                new UnsupportedOperationException("Wiremock implementation required"));
    }
}
