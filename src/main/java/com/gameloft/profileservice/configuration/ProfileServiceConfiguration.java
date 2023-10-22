package com.gameloft.profileservice.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameloft.profileservice.client.CampaignClient;
import com.gameloft.profileservice.dto.campaign.CampaignDto;
import com.gameloft.profileservice.dto.profile.ProfileDto;
import com.gameloft.profileservice.model.profile.Profile;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

import static org.mockito.Mockito.when;

@Configuration
public class ProfileServiceConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(ProfileDto.class, Profile.class)
                        .addMappings(
                                mapper -> {
                                    mapper.map(ProfileDto::getPlayerId, Profile::setPlayerId);
                                }
                        );
        modelMapper.typeMap(Profile.class, ProfileDto.class)
                .addMappings(
                        mapper -> {
                            mapper.map(Profile::getPlayerId, ProfileDto::setPlayerId);
                        }
                );
        return modelMapper;
    }

    @Bean
    public CampaignClient stubbedCampaignClient(ObjectMapper objectMapper) throws IOException {
        //todo replace with Wiremock
        CampaignClient campaignMock = Mockito.mock(CampaignClient.class);
        when(campaignMock.retrieveCurrentCampaign()).thenReturn(constructCampaignStubs(objectMapper));
        return campaignMock;
    }

    private Collection<CampaignDto> constructCampaignStubs(ObjectMapper objectMapper) throws IOException {
        return objectMapper.readValue(
                Files.readAllBytes(Paths.get("src/main/resources/stubs/currentCampaign.json")),
                new TypeReference<Collection<CampaignDto>>() {
                });
    }
}
