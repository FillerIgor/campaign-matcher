package com.gameloft.profileservice.service;

import com.gameloft.profileservice.dto.profile.ProfileDto;
import com.gameloft.profileservice.dto.response.PageableProfileResponseDto;
import com.gameloft.profileservice.model.profile.Profile;
import com.gameloft.profileservice.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {
    //todo add logs
    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;
    private final CampaignMatcher campaignMatcher;

    public Optional<ProfileDto> findByProfileId(Long profileId) {
        return profileRepository.findByProfileId(profileId)
                .map(profile -> modelMapper.map(profile, ProfileDto.class));
    }

    public PageableProfileResponseDto findAll(Pageable pageable) {
        Page<Profile> result = profileRepository.findAll(pageable);
        List<ProfileDto> profiles = result.get()
                .map(profile -> modelMapper.map(profile, ProfileDto.class))
                .collect(Collectors.toList());
        Pageable currentPage = result.getPageable();
        return PageableProfileResponseDto.builder()
                .profiles(profiles)
                .pageable(currentPage)
                .build();
    }

    public ProfileDto save(ProfileDto requestBody) {
        Profile profile = modelMapper.map(requestBody, Profile.class);
        Profile savedProfile = profileRepository.save(profile);
        return modelMapper.map(savedProfile, ProfileDto.class);
    }

    @Transactional
    public PageableProfileResponseDto retrieveProfileWithCurrentCampaign(UUID playerId) {
        Set<ProfileDto> updatedProfiles = profileRepository.findByPlayerId(playerId)
                .stream()
                .filter(campaignMatcher::matchActiveCampaigns)
                .map(profile -> modelMapper.map(profile, ProfileDto.class))
                .collect(Collectors.toSet());
        return PageableProfileResponseDto.builder()
                .profiles(updatedProfiles)
                .build();
    }
}
