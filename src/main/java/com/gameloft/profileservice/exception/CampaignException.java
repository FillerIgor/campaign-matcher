package com.gameloft.profileservice.exception;

public class CampaignException extends ProfileServiceException{
    public CampaignException(String message) {
        super(message);
    }

    public CampaignException(String message, Throwable cause) {
        super(message, cause);
    }
}
