package com.gameloft.profileservice.service.validation;

import com.gameloft.profileservice.dto.campaign.CampaignDto;
import com.gameloft.profileservice.dto.campaign.DoesNotHaveDto;
import com.gameloft.profileservice.dto.campaign.HasDto;
import com.gameloft.profileservice.dto.campaign.MatchersDto;
import com.gameloft.profileservice.dto.profile.ProfileDto;
import com.gameloft.profileservice.model.profile.Profile;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.internal.util.Objects;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class CampaignItemsValidator implements CampaignValidator {
    @Override
    public boolean validate(CampaignDto campaign, Profile profile) {
        if (profile == null) {
            return false;
        }

        return Optional.ofNullable(campaign)
                .map(CampaignDto::getMatchers)
                .filter(matchers -> areValidItems(matchers, profile))
                .isPresent();
    }

    private boolean areValidItems(MatchersDto matchers, Profile profile) {
        Map<String, Long> inventory = Objects.firstNonNull(profile.getInventory(), Map.<String, Long>of());

        boolean matchedCounty = isMatchedCountry(matchers.getHas(), profile.getCountry());
        boolean matchedMandatoryItems = areMatchedItems(matchers.getHas(), inventory.keySet());
        boolean notMatchedMandatoryItems = areNotMatchedItems(matchers.getDoesNotHave(), inventory.keySet());

        return matchedCounty && matchedMandatoryItems && notMatchedMandatoryItems;
    }

    private boolean isMatchedCountry(HasDto mandatoryCriteria, String country) {
        if (mandatoryCriteria == null) {
            return false;
        }
        return Optional.ofNullable(mandatoryCriteria.getCountry())
                .filter(campaignCountries -> campaignCountries.stream()
                        .anyMatch(campaignCountry -> StringUtils.equalsAnyIgnoreCase(campaignCountry, country)))
                .isPresent();
    }

    private boolean areMatchedItems(HasDto mandatoryCriteria, Set<String> items) {
        if (mandatoryCriteria == null) {
            return false;
        }
        return Optional.ofNullable(mandatoryCriteria.getItems())
                .filter(items::containsAll)
                .isPresent();
    }

    private boolean areNotMatchedItems(DoesNotHaveDto criteria, Set<String> items) {
        if (criteria == null) {
            return false;
        }
        return Optional.ofNullable(criteria.getItems())
                .filter(campaignItems -> !items.containsAll(campaignItems))
                .isPresent();
    }
}
