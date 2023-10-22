package com.gameloft.profileservice.service.validation;

import com.gameloft.profileservice.dto.campaign.CampaignDto;
import com.gameloft.profileservice.dto.campaign.LevelDto;
import com.gameloft.profileservice.dto.campaign.MatchersDto;
import com.gameloft.profileservice.dto.profile.ProfileDto;
import com.gameloft.profileservice.model.profile.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CampaignLevelValidator implements CampaignValidator {
    @Override
    public boolean validate(CampaignDto campaign, Profile profile) {

        if (profile == null || profile.getLevel() == null) {
            return false;
        }

        return Optional.ofNullable(campaign)
                .map(CampaignDto::getMatchers)
                .map(MatchersDto::getLevel)
                .filter(level -> isLevelValid(level, profile.getLevel()))
                .isPresent();
    }

    private boolean isLevelValid(LevelDto level, Long profileLevel) {
        Long min = level.getMin();
        Long max = level.getMax();
        return (min != null && profileLevel >= min) &&
                (max != null && profileLevel <= max);
    }
}
