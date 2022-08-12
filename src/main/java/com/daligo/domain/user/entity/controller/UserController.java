package com.daligo.domain.user.entity.controller;

import com.daligo.domain.user.entity.entity.UserEntity;
import com.daligo.domain.user.entity.service.UserService;
import com.daligo.global.response.GeneralResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "유저 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {
// user API는 Security가 적용되어야할 것 같아서 대충 만들어봤습니다.

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
    public ResponseEntity<GeneralResponse> getExample(){
        return new ResponseEntity<>(GeneralResponse.of(HttpStatus.OK, "예시"), HttpStatus.OK);
    }

    @Operation(tags = "User", summary = "유저의 정보를 가져옵니다.",
            responses={
                    @ApiResponse(responseCode = "200", description = "유저 정보 조회 성공",
                            content = @Content(schema = @Schema(implementation = UserEntity.class)))
            })
    @GetMapping("/user")
    public ResponseEntity<UserEntity> getUserEntityExample(){
        UserEntity userEntity = UserEntity.builder()
                .userId(Long.valueOf("example"))
                .name("달리고")
                .phone("010-0000-0000")
                .age(Integer.parseInt("26"))
                .location("부산")
                .email("이메일")
                .build();
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

}
