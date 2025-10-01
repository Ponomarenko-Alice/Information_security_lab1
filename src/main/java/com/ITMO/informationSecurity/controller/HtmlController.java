package com.ITMO.informationSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class HtmlController {
    @GetMapping
    public CommentDto get() {
        return new CommentDto("<script>alert('XSS')</script>", "ok");
    }

    public record CommentDto(String text, String status) {
    }
}