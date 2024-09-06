package com.openai.openai.domain.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;

interface Assistant {

        String chat(@MemoryId int memoryId, @UserMessage String description);

    }