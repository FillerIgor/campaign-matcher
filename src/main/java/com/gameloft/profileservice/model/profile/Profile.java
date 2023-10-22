package com.gameloft.profileservice.model.profile;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long profileId;
    private UUID playerId;

    @ElementCollection
    private Collection<String> activeCampaigns;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private Clan clan;
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Device> devices;
    @ElementCollection
    @CollectionTable(name = "profile_inventory_mapping",
            joinColumns = {@JoinColumn(name = "inventory_id")})
    @MapKeyColumn(name = "inventory_item_name")
    @Column(name = "inventory_item_amount")
    private Map<String, Long> inventory;
    private String gender;
    private String credential;
    private String country;
    private String language;
    private Long level;
    private Long totalPlaytime;
    private Long totalRefund;
    private Long totalSpent;
    private Long totalTransactions;
    private Long xp;
    private ZonedDateTime birthdate;
    private ZonedDateTime created;
    private ZonedDateTime modified;
    private ZonedDateTime lastPurchase;
    private ZonedDateTime lastSession;
    private String customField;
}
