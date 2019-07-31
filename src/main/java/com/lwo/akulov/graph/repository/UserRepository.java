package com.lwo.akulov.graph.repository;

import com.lwo.akulov.graph.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, UserCustomRepository {

    List<User> findByIsActive(Boolean isActive);

//    @Query(nativeQuery = true, value = "select * from users u where u.isactive = :isActive AND u.email  :email")
    Page<User> findAll(Pageable pageable);
}
