package com.lwo.akulov.graph.graphs;

import com.lwo.akulov.graph.entity.User;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import io.leangen.graphql.spqr.spring.util.ConcurrentMultiRegistry;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.ConcurrentLinkedQueue;

@AllArgsConstructor
@Data
@Component
@GraphQLApi
public class Subscriber/* implements GraphQLSubscriptionResolver */{

    private final ConcurrentMultiRegistry<Long, FluxSink<User>> subscribers = new ConcurrentMultiRegistry<>();
    private final ConcurrentLinkedQueue<FluxSink<User>> queue = new ConcurrentLinkedQueue<>();

    @GraphQLSubscription
    public Publisher<User> changeUser(Long id) {
        return Flux.create(subscriber -> subscribers.add(id, subscriber.onDispose(() -> subscribers.remove(id, subscriber))), FluxSink.OverflowStrategy.LATEST);
    }

    @GraphQLSubscription
    public Publisher<User> changeUserList() {
        return Flux.create(subscriber -> queue.add(subscriber.onDispose(() -> queue.remove(subscriber))), FluxSink.OverflowStrategy.LATEST);
    }
}
