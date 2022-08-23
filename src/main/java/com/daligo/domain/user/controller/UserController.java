package com.daligo.domain.user.controller;

import com.daligo.security.UserPrincipal;
import com.daligo.domain.user.dao.JpaUserRepository;
import com.daligo.domain.user.entity.UserEntity;
import com.daligo.global.response.GeneralResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "유저 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final JpaUserRepository userRepository;

    private static final String Example = "{\n" +
            "    \"code\" : 200\n" +
            "    \"message\" : \"예시\"\n" +
            "}";

    @Operation(tags = "User", summary = "예시 API.",
            responses={
                    @ApiResponse(responseCode = "200", description = "예시",
                            content = @Content(schema = @Schema(implementation = GeneralResponse.class),
                                    examples = @ExampleObject(value = Example)))
            })
    @GetMapping("/example")
    public String getExample(){
        return "hello";
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserEntity getCurrentUser(@AuthenticationPrincipal UserPrincipal user) {
        return userRepository.findById(user.getId()).orElseThrow(() -> new IllegalStateException("not found user"));
    }

}
