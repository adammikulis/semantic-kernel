// Copyright (c) Microsoft. All rights reserved.
package com.microsoft.semantickernel.chatcompletion;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import com.microsoft.semantickernel.KernelContent;
import com.microsoft.semantickernel.orchestration.ContextVariable;

public class ChatMessageContent extends KernelContent {

    private AuthorRole authorRole;
    private String content;
    private List<KernelContent> items;
    private Charset encoding;

    public ChatMessageContent(
        AuthorRole authorRole,
        String content,
        String modelId,
        Object innerContent,
        Charset encoding,
        Map<String, ContextVariable<?>> metadata
    ) {
        super(innerContent, modelId, metadata);
        this.authorRole = authorRole;
        this.content = content;
        this.encoding = encoding != null ? encoding : StandardCharsets.UTF_8;
    }

    public ChatMessageContent(
        AuthorRole authorRole,
        List<KernelContent> items,
        String modelId,
        Object innerContent,
        Charset encoding,
        Map<String, ContextVariable<?>> metadata
    ) {
        super(innerContent, modelId, metadata);
        this.authorRole = authorRole;
        this.encoding = encoding != null ? encoding : StandardCharsets.UTF_8;
        this.items = items;
    }

    public AuthorRole getAuthorRole() {
        return authorRole;
    }

    public void setAuthorRole(AuthorRole authorRole) {
        this.authorRole = authorRole;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<KernelContent> getItems() {
        return items;
    }

    public void setItems(List<KernelContent> items) {
        this.items = items;
    }

    public Charset getEncoding() {
        return encoding;
    }

    public void setEncoding(Charset encoding) {
        this.encoding = encoding;
    }

    @Override
    public String toString() {
        return content != null ? content : "";
    }
}
