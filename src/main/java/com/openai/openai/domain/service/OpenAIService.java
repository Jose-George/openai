package com.openai.openai.domain.service;

import com.openai.security.core.context.ContextSecurity;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;

@Service
public class OpenAIService {

    @Value("${langchain4j.open-ai.chat-model.api-key}")
    private String appKey;

    private final ContextSecurity contextSecurity;
    private final PersistentChatMemoryStore store;

    public OpenAIService(ContextSecurity contextSecurity, PersistentChatMemoryStore store) {
        this.contextSecurity = contextSecurity;
        this.store = store;
    }

    public String chat(String description) {
        Assistant assistant = createAssistant();
        return assistant.chat(getUserId(), description);
    }

    private Assistant createAssistant() {
        ChatMemoryProvider chatMemoryProvider = memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(5)
                .chatMemoryStore(store)
                .build();

        OpenAiChatModel chatModel = OpenAiChatModel.builder()
                .modelName(GPT_4_O)
                .apiKey(appKey)
                .build();

        return AiServices.builder(Assistant.class)
                .chatLanguageModel(chatModel)
                .chatMemoryProvider(chatMemoryProvider)
                .build();
    }

    private int getUserId() {
        return Integer.parseInt(contextSecurity.getUserId());
    }
}