package com.gameloft.profileservice.service.validation;

import com.gameloft.profileservice.dto.campaign.CampaignDto;
import com.gameloft.profileservice.dto.profile.ProfileDto;
import com.gameloft.profileservice.model.profile.Profile;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

//@Component
//todo enable if needed
public class CampaignDatesValidator implements CampaignValidator{
    @Override
    public boolean validate(CampaignDto campaign, Profile profile) {
        if (profile == null) {
            return false;
        }

        ZonedDateTime campaignStartDate = campaign.getStartDate();
        ZonedDateTime campaignEndDate = campaign.getEndDate();
        ZonedDateTime profileLastSession = profile.getLastSession();
        return profileLastSession.isAfter(campaignStartDate) && profileLastSession.isBefore(campaignEndDate);
    }
}
