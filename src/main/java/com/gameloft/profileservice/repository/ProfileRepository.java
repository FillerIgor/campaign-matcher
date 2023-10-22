package com.gameloft.profileservice.repository;

import com.gameloft.profileservice.model.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByProfileId(Long profileId);

    List<Profile> findByPlayerId(UUID playerId);
}
