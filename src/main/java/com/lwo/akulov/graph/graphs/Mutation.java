package com.lwo.akulov.graph.graphs;

import com.lwo.akulov.graph.entity.User;
import com.lwo.akulov.graph.serivice.UserService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
@GraphQLApi
public class Mutation/* implements GraphQLMutationResolver */{

    private UserService userService;
    private Subscriber subscriber;

    @Autowired
    public Mutation(UserService userService, Subscriber subscriber) {
        this.userService = userService;
        this.subscriber = subscriber;
    }

    @GraphQLMutation
    public User newUser(@GraphQLNonNull String fName,
                        @GraphQLNonNull String mName,
                        @GraphQLNonNull String lName,
                        @GraphQLNonNull String eMail) {
        User user = userService.createUser(fName, mName, lName, eMail);
        subscriber.getQueue().forEach(subscriber -> subscriber.next(user));
        return user;
    }

    @GraphQLMutation
    public User deleteUser(@GraphQLNonNull Long id) {
        User user = userService.deleteUser(id);
        subscriber.getQueue().forEach(subscriber -> subscriber.next(user));
        return user;
    }

    @GraphQLMutation
    public User activateUser(@GraphQLNonNull Long id) {
        User user = userService.activateUser(id);
        subscriber.getQueue().forEach(subscriber -> subscriber.next(user));
        return user;
    }

    @GraphQLMutation
    public User updateUser(@GraphQLNonNull Long id,
                           String fName,
                           String mName,
                           String lName,
                           String eMail) {
        User user = userService.updateUser(id, fName, mName, lName, eMail);
        subscriber.getSubscribers()
                .get(id)
                .forEach(subscriber -> subscriber.next(user));
        subscriber
                .getQueue()
                .forEach(subscriber -> subscriber.next(user));
        return user;
    }
}
