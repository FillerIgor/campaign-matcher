package com.gameloft.profileservice.dto.response;

import com.gameloft.profileservice.dto.profile.ProfileDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

@Data
@Builder
public class PageableProfileResponseDto {
    private Collection<ProfileDto> profiles;
    private Pageable pageable;
}
