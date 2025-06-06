package com.iolab.musicstore.api.rest;

import com.iolab.musicstore.application.dto.response.SingleDataResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<SingleDataResponse<String>> hello() {
        return ResponseEntity.ok(SingleDataResponse.success("hello"));
    }
}
