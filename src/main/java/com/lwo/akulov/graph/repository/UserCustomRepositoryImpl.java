package com.lwo.akulov.graph.repository;

import com.lwo.akulov.graph.dto.PaginationDto;
import com.lwo.akulov.graph.dto.PaginationUserRequest;
import com.lwo.akulov.graph.entity.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

import static com.lwo.akulov.graph.entity.QUser.user;
import static java.util.Objects.isNull;

public class UserCustomRepositoryImpl implements UserCustomRepository {

    private EntityManager em;

    @Autowired
    public UserCustomRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    public PaginationDto getAllByParam(PaginationUserRequest paginationUserRequest) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(user.isActive.isTrue());
        if (!isNull(paginationUserRequest.getEMail())) {
            booleanBuilder.and((user.eMail.toLowerCase()).contains(paginationUserRequest.getEMail().toLowerCase()));
        }
        if (!isNull(paginationUserRequest.getMName())) {
            booleanBuilder.and(user.mName.toLowerCase().contains(paginationUserRequest.getMName().toLowerCase()));
        }
        if (!isNull(paginationUserRequest.getLName())) {
            booleanBuilder.and((user.lName.toLowerCase()).contains(paginationUserRequest.getLName().toLowerCase()));
        }
        if (!isNull(paginationUserRequest.getFName())) {
            booleanBuilder.and(user.fName.toLowerCase().contains(paginationUserRequest.getFName().toLowerCase()));
        }

        Long totalElements = new JPAQuery<User>(em)
                .select(user)
                .from(user)
                .where(booleanBuilder.getValue())
                .fetchCount();

        List<User> userList = new JPAQuery<User>(em)
                .select(user)
                .from(user)
                .where(booleanBuilder.getValue())
                .limit(paginationUserRequest.getPerPage())
                .offset((paginationUserRequest.getPage() -1 )*paginationUserRequest.getPerPage())
                .fetch();

/*        Map<String, String> params = new HashMap<>();
        StringBuilder queryString = new StringBuilder();
        queryString.append("select u from User u where u.isActive = true ");
        if (!isNull(paginationUserRequest.getEMail())) {
            params.put("email", aroundBy(paginationUserRequest.getEMail(), "%"));
            queryString.append("AND LOWER(u.eMail) like LOWER(:email) ");
        }
        if (!isNull(paginationUserRequest.getFName())) {
            params.put("fName", aroundBy(paginationUserRequest.getFName(), "%"));
            queryString.append("AND LOWER(u.fName) like LOWER(:fName) ");
        }

        if (!isNull(paginationUserRequest.getMName())) {
            params.put("mName", aroundBy(paginationUserRequest.getMName(), "%"));
            queryString.append("AND LOWER(u.mName) like LOWER(:mName) ");
        }
        if (!isNull(paginationUserRequest.getLName())) {
            params.put("lName", aroundBy(paginationUserRequest.getLName(), "%"));
            queryString.append("AND LOWER(u.lName) like LOWER(:lName) ");
        }
        queryString.append("limit = :limit offset = :offset")*/
/*        String resultQuery = queryString.toString();
        Query query = em.createQuery(resultQuery);
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }

        List<User> list = query.getResultList();
        return PaginationDto.builder()
                .perPage(paginationUserRequest.getPerPage() > 0 ? paginationUserRequest.getPerPage() : 25)
                .page(paginationUserRequest.getPage() > 0 ? paginationUserRequest.getPage() : 1)
                .haveData(list.size() > 0)
                .data(list)
                .totalElements(list.size())
                .build();*/

        return PaginationDto.builder()
                .perPage(paginationUserRequest.getPerPage() > 0 ? paginationUserRequest.getPerPage() : 25)
                .page(paginationUserRequest.getPage() > 0 ? paginationUserRequest.getPage() : 1)
                .haveData(userList.size() > 0)
                .data(userList)
                .totalElements(totalElements)
                .build();
    }
}
