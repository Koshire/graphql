package com.lwo.akulov.graph.graphs;

import com.lwo.akulov.graph.dto.PaginationDto;
import com.lwo.akulov.graph.dto.PaginationUserRequest;
import com.lwo.akulov.graph.entity.Address;
import com.lwo.akulov.graph.entity.User;
import com.lwo.akulov.graph.mapper.PaginationMapper;
import com.lwo.akulov.graph.repository.AddressRepository;
import com.lwo.akulov.graph.repository.UserRepository;
import com.lwo.akulov.graph.serivice.UserService;
import io.leangen.graphql.annotations.GraphQLComplexity;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@GraphQLApi
public class Query/* implements GraphQLQueryResolver */{

    private final String CURRENCY_RATES_URL = "http://www.nbrb.by/API/ExRates/Rates/";

    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private UserService userService;
    private PaginationMapper paginationMapper = PaginationMapper.getInstance();

    @Autowired
    public Query(UserRepository userRepository,
                 AddressRepository addressRepository,
                 UserService userService) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    @GraphQLQuery
    public void getCurrencyRate(Integer currencyId) {

    }

    @GraphQLQuery
    public Iterable<User> findAllUser() {
        return userRepository.findAll();
    }

    @GraphQLQuery
    public List<User> findByActive(Boolean isActive) {
        return userService.findByActive(isActive);
    }

    @GraphQLQuery
    public Long countUser() {
        return userRepository.count();
    }

    @GraphQLQuery
    public PaginationDto getAllByPage(int pageNumber, int pageSize) {
        Page<User> page = userService.allUsersByPage(pageNumber, pageSize);
        return paginationMapper.entityToDto(page);
    }

    @GraphQLQuery
    public PaginationDto getAllByParams(PaginationUserRequest paginationUserRequest) {
        return userService.allUsersByPage(paginationUserRequest);
    }

    @GraphQLQuery(name = "findOneUser")
    public User findOneUser(Long id) {
        return userRepository.findById(id).orElse(User.builder()
                .id(0L)
                .build());
    }

    @GraphQLQuery(name = "findById")
    @GraphQLComplexity("20")
    public Address findById(Long id) {
        return addressRepository.findById(id).orElse(Address.builder()
                .id(0L)
                .build());
    }
}
