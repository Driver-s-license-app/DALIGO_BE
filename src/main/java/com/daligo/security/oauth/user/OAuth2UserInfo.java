package com.daligo.security.oauth.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@AllArgsConstructor
public abstract class OAuth2UserInfo {

    protected Map<String, Object> attributes;

    public abstract String getId();
    public abstract String getEmail();
    public abstract String getName();
    public abstract String getImageUrl();

}