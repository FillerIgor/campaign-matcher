package com.gameloft.profileservice.service;

import com.gameloft.profileservice.client.CampaignClient;
import com.gameloft.profileservice.dto.campaign.CampaignDto;
import com.gameloft.profileservice.dto.profile.ProfileDto;
import com.gameloft.profileservice.model.profile.Profile;
import com.gameloft.profileservice.service.validation.CampaignValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CampaignMatcher {

    private final CampaignClient stubbedCampaignClient; //todo replace with real implementation
    private final List<CampaignValidator> validators;

    public boolean matchActiveCampaigns(Profile profile) {
        Collection<CampaignDto> campaigns = stubbedCampaignClient.retrieveCurrentCampaign();
        Set<String> matchedCampaigns = campaigns.stream()
                .filter(campaign -> matchActiveCampaign(campaign, profile))
                .map(CampaignDto::getName)
                .collect(Collectors.toSet());
        profile.setActiveCampaigns(matchedCampaigns);
        return !matchedCampaigns.isEmpty();
    }

    public boolean matchActiveCampaign(CampaignDto campaign, Profile profile) {
        if (!campaign.getEnabled()){
            return false;
        }
        return validators.stream()
                        .allMatch(validator -> validator.validate(campaign, profile));
    }
}
