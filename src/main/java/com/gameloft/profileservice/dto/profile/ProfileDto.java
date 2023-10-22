package com.gameloft.profileservice.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gameloft.profileservice.model.profile.Clan;
import com.gameloft.profileservice.model.profile.Device;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class ProfileDto {
    @JsonProperty("player_id")
    private UUID playerId;
    @JsonProperty("active_campaigns")
    private Collection<String> activeCampaigns;
    private Clan clan;
    private List<Device> devices;
    private Map<String, Long> inventory;
    private String gender;
    private String credential;
    private String country;
    private String language;
    private Long level;
    @JsonProperty("total_playtime")
    private Long totalPlaytime;
    @JsonProperty("total_refund")
    private Long totalRefund;
    @JsonProperty("total_spent")
    private Long totalSpent;
    @JsonProperty("total_transactions")
    private Long totalTransactions;
    private Long xp;
    private ZonedDateTime birthdate;
    private ZonedDateTime created;
    private ZonedDateTime modified;
    @JsonProperty("last_purchase")
    private ZonedDateTime lastPurchase;
    @JsonProperty("last_session")
    private ZonedDateTime lastSession;
    @JsonProperty("_customField")
    private String customField;
}
