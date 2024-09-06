package com.openai.openai.api.model;

public record AnswerResponse(String answer) {

    public static AnswerResponse toModel(String answer){
        return new AnswerResponse(answer);
    }

}
