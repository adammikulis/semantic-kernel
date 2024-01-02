package com.microsoft.semantickernel;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

=======
>>>>>>> da2ac37b4bcacd0ab068a8ef74d2b0a828b4795c
import com.microsoft.semantickernel.chatcompletion.ChatCompletionService;
import com.microsoft.semantickernel.orchestration.KernelFunction;
import com.microsoft.semantickernel.orchestration.contextvariables.ContextVariable;
import com.microsoft.semantickernel.orchestration.contextvariables.ContextVariableType;
import com.microsoft.semantickernel.orchestration.contextvariables.ContextVariableTypes;
import com.microsoft.semantickernel.orchestration.contextvariables.KernelArguments;
<<<<<<< HEAD
import com.microsoft.semantickernel.semanticfunctions.PromptTemplate;
import com.microsoft.semantickernel.textcompletion.TextGenerationService;

=======
import com.microsoft.semantickernel.plugin.KernelPlugin;
import com.microsoft.semantickernel.semanticfunctions.PromptTemplate;
import com.microsoft.semantickernel.textcompletion.TextGenerationService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
>>>>>>> da2ac37b4bcacd0ab068a8ef74d2b0a828b4795c
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DefaultKernel implements Kernel {

    private final ServiceProvider serviceProvider;
<<<<<<< HEAD
    private final List<KernelFunction> functions;

    public DefaultKernel(ServiceProvider serviceProvider, List<KernelFunction> plugins) {
        this.serviceProvider = serviceProvider;
        if (plugins != null) {
            List<KernelFunction> temp = new ArrayList<>();
            temp.forEach(temp::add);
            this.functions = Collections.unmodifiableList(temp);
        } else {
            this.functions = Collections.<KernelFunction>emptyList();
        }
=======

    public DefaultKernel(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
>>>>>>> da2ac37b4bcacd0ab068a8ef74d2b0a828b4795c
    }

    @Override
    public <T> Mono<ContextVariable<T>> invokeAsync(KernelFunction function,
        @Nullable KernelArguments arguments, ContextVariableType<T> resultType) {
        return function.invokeAsync(this, arguments, resultType);
    }

    @Override
    public <T> Mono<ContextVariable<T>> invokeAsync(KernelFunction function,
        @Nullable KernelArguments arguments, Class<T> resultType) {
        return function.invokeAsync(this, arguments,
            ContextVariableTypes.getDefaultVariableTypeForClass(resultType));
    }

    @Override
    public <T> Flux<T> invokeStreamingAsync(KernelFunction function,
        @Nullable KernelArguments arguments, ContextVariableType<T> resultType) {
        return function.invokeStreamingAsync(this, arguments, resultType)
            .map(x -> x.innerContent);
    }

    @Override
    public <T> Flux<T> invokeStreamingAsync(KernelFunction function,
        @Nullable KernelArguments arguments, Class<T> resultType) {
        return function.invokeStreamingAsync(this, arguments,
                ContextVariableTypes.getDefaultVariableTypeForClass(resultType))
            .map(x -> x.innerContent);
    }

    @Override
    public List<KernelFunction> getFunctions() {
        return functions;
    }

    @Override
    public ServiceProvider getServiceSelector() {
        return serviceProvider;
    }

    static class DefaultServiceProvider implements ServiceProvider {

        private final Map<Class, AIService> services;

        public DefaultServiceProvider(Map<Class, AIService> services) {
            this.services = services;
        }

        @Nullable
        @Override
        public <T> T getService(Class<T> clazz) {
            T service = (T) services.get(clazz);

            if (service == null && clazz.equals(TextGenerationService.class)) {
                service = (T) services.get(ChatCompletionService.class);
            }

            return service;
        }
    }

    public static class Builder implements Kernel.Builder {

        private AIService defaultAIService;
        private final Map<Class, AIService> services = new HashMap<>();
<<<<<<< HEAD
        private final List<KernelFunction> functions = new ArrayList<>();
=======
        private final List<KernelPlugin> plugins = new ArrayList<>();
>>>>>>> da2ac37b4bcacd0ab068a8ef74d2b0a828b4795c

        @Override
        public <T extends AIService> Builder withDefaultAIService(Class<T> clazz, T aiService) {
            this.defaultAIService = aiService;
            services.put(clazz, aiService);
            return this;
        }

        @Override
        public Builder withPromptTemplateEngine(PromptTemplate promptTemplate) {
            return this;
        }

        @Override
<<<<<<< HEAD
        public Kernel.Builder withFunction(KernelFunction function) {
            functions.add(function);
=======
        public Kernel.Builder withPlugins(KernelPlugin plugin) {
            plugins.add(plugin);
>>>>>>> da2ac37b4bcacd0ab068a8ef74d2b0a828b4795c
            return this;
        }

        @Override
        public Kernel build() {
<<<<<<< HEAD
            return new DefaultKernel(new DefaultServiceProvider(services), functions);
=======
            return new DefaultKernel(new DefaultServiceProvider(services));
>>>>>>> da2ac37b4bcacd0ab068a8ef74d2b0a828b4795c
        }
    }
}
