package com.gameloft.profileservice.service.validation;

import com.gameloft.profileservice.dto.campaign.CampaignDto;
import com.gameloft.profileservice.dto.profile.ProfileDto;
import com.gameloft.profileservice.model.profile.Profile;

public interface CampaignValidator {

    boolean validate(CampaignDto campaign, Profile profile);
}
