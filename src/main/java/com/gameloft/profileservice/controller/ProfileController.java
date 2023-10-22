package com.gameloft.profileservice.controller;

import com.gameloft.profileservice.dto.profile.ProfileDto;
import com.gameloft.profileservice.dto.response.PageableProfileResponseDto;
import com.gameloft.profileservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<PageableProfileResponseDto> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(profileService.findAll(pageable));
    }

    @GetMapping("{profileId}")
    public ResponseEntity<ProfileDto> findByProfileId(@PathVariable Long profileId) {
        return profileService.findByProfileId(profileId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/players/{playerId}/campaigns/current")
    public ResponseEntity<PageableProfileResponseDto> alignCurrentCampaign(@PathVariable UUID playerId) {
        return Optional.ofNullable(profileService.retrieveProfileWithCurrentCampaign(playerId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProfileDto> save(@RequestBody ProfileDto requestBody) {
        return ResponseEntity.ok(profileService.save(requestBody));
    }
}
