package com.daligo.security.oauth;

import com.daligo.domain.user.entity.SocialAuth;
import com.daligo.security.UserPrincipal;
import com.daligo.domain.user.dao.JpaUserRepository;
import com.daligo.global.entity.AuthProvider;
import com.daligo.domain.user.entity.UserEntity;
import com.daligo.global.entity.UserRole;
import com.daligo.global.exception.OAuth2AuthenticationProcessingException;
import com.daligo.security.oauth.user.OAuth2UserInfo;
import com.daligo.security.oauth.user.OAuth2UserInfoFactory;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final JpaUserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
                oAuth2UserRequest.getClientRegistration().getRegistrationId(),
                oAuth2User.getAttributes()
        );

        if (StringUtils.isBlank(oAuth2UserInfo.getEmail()))
            throw new OAuth2AuthenticationProcessingException("empty email");

        Optional<UserEntity> userOptional = userRepository.findFirstByEmailOrderByIdAsc(oAuth2UserInfo.getEmail());
        UserEntity user;
        if (userOptional.isPresent()) {
            if (!userOptional.get().getSocialAuth().getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId())))
                throw new OAuth2AuthenticationProcessingException("already sign up other provider");
            user = updateUser(userOptional.get(), oAuth2UserInfo);
        } else {
            user = registerUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private UserEntity registerUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        return userRepository.save(UserEntity.builder()
                .email(oAuth2UserInfo.getEmail())
                .nickname(oAuth2UserInfo.getName())
                .socialAuth(SocialAuth.builder()
                        .providerId(oAuth2UserInfo.getId())
                        .provider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
                        .email(oAuth2UserInfo.getEmail())
                        .name(oAuth2UserInfo.getName())
                        .imageUrl(oAuth2UserInfo.getImageUrl())
                        .attributes(oAuth2UserInfo.getAttributes().toString())
                        .ip("127.0.0.1")
                        .build())
                .build());
    }

    private UserEntity updateUser(UserEntity user, OAuth2UserInfo oAuth2UserInfo) {
        user.getSocialAuth().update(oAuth2UserInfo.getName(), oAuth2UserInfo.getImageUrl(), oAuth2UserInfo.getAttributes());
        return user;
    }

}