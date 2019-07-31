package com.lwo.akulov.graph.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GraphConfig {
/*
    private final GraphQLSchema graphQLSchema;

    @Bean
        public GraphQL graphQL() {
            return GraphQL.newGraphQL(graphQLSchema)
                    .instrumentation(new MaxQueryDepthInstrumentation(2))
                    .build();
*/

/*        return GraphQL.newGraphQL(graphQLSchema)
                .instrumentation(new MaxQueryComplexityInstrumentation(10))
                .build();*/
}

