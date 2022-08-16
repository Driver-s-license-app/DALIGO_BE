package com.daligo.domain.comment.controller;

import com.daligo.domain.comment.dto.CommentReadResponseDto;
import com.daligo.domain.comment.dto.CommentWriteRequestDto;
import com.daligo.domain.comment.dto.CommentWriteResponseDto;
import com.daligo.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentWriteResponseDto> write(
            @Valid @RequestBody CommentWriteRequestDto dto
    ) {
        CommentWriteResponseDto response = commentService.write(dto);

        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping
    public ResponseEntity<CommentReadResponseDto> read(@RequestParam Long id) {
        CommentReadResponseDto response = commentService.read(id);

        return ResponseEntity.ok()
                .body(response);
    }
}
