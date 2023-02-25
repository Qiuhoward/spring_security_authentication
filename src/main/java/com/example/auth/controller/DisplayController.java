package com.example.auth.controller;


import com.example.auth.dto.ChoicePageResponse;
import com.example.auth.dto.MkdirRequest;
import com.example.auth.service.DisplayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "display")
public class DisplayController <T>{

    private final DisplayService service;

    public DisplayController(DisplayService service) {
        this.service = service;
    }

    @PostMapping(value = "choice_page")
    public ResponseEntity<ChoicePageResponse>displayPage(@RequestParam("pageId") int pageId){
        return ResponseEntity.ok(service.findAll(pageId));
    }

    @PostMapping(value = "mkdir")
    public ResponseEntity<String> mkdir(@RequestBody MkdirRequest request){
        return ResponseEntity.ok(service.mkdir(request));
    }
}
