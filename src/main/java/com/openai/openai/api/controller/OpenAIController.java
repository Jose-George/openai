package com.openai.openai.api.controller;


import com.openai.openai.api.model.AnswerResponse;
import com.openai.openai.domain.service.OpenAIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/sophia")
@RestController
public class OpenAIController {

    private final OpenAIService service;

    public OpenAIController(OpenAIService service) {
        this.service = service;
    }

    @PostMapping("/chat")
    public ResponseEntity<AnswerResponse> chat(@RequestBody String message) {
        var response = service.chat(message);
        var answerResponse = AnswerResponse.toModel(response);
        return ResponseEntity.ok(answerResponse);
    }

}
