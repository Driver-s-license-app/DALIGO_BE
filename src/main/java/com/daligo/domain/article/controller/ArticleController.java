package com.daligo.domain.article.controller;

import com.daligo.domain.article.dto.ArticleReadResponseDto;
import com.daligo.domain.article.dto.ArticleWriteRequestDto;
import com.daligo.domain.article.dto.ArticleWriteResponseDto;
import com.daligo.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleWriteResponseDto> write(
            @Valid @RequestBody ArticleWriteRequestDto dto) {
        ArticleWriteResponseDto response = articleService.write(dto);

        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping
    public ResponseEntity<ArticleReadResponseDto> read(@RequestParam Long id) {
        ArticleReadResponseDto response = articleService.read(id);

        return ResponseEntity.ok()
                .body(response);
    }
}
