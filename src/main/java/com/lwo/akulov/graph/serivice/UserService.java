package com.lwo.akulov.graph.serivice;

import com.lwo.akulov.graph.dto.PaginationDto;
import com.lwo.akulov.graph.dto.PaginationUserRequest;
import com.lwo.akulov.graph.entity.User;
import com.lwo.akulov.graph.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findByActive(Boolean isActive) {
        return userRepository.findByIsActive(isActive);
    }

    public User updateUser(Long id, String fName, String mName, String lName, String eMail) {

        User user = userRepository.findById(id).orElse(User.builder()
                .id(0L)
                .isActive(false)
                .build());
        if (user.getId() > 0) {
            user = userRepository.save(User.builder()
                    .id(id)
                    .fName(existValue(fName) ? fName : user.getFName())
                    .mName(existValue(mName) ? mName : user.getMName())
                    .lName(existValue(lName) ? lName : user.getLName())
                    .eMail(existValue(eMail) ? eMail : user.getEMail())
                    .isActive(user.getIsActive())
                    .address(user.getAddress())
                    .build());
        }
        return user;
    }

    private boolean existValue(Object value) {
        return value != null;
    }

    public User deleteUser(Long id) {
        User user = null;
        if (userRepository.findById(id).isPresent()) {
            user = userRepository.findById(id).get();
            user.setIsActive(false);
            user = userRepository.save(user);
        }
        return user;
    }

    public User activateUser(Long id) {
        User user = null;
        if (userRepository.findById(id).isPresent()) {
            user = userRepository.findById(id).get();
            user.setIsActive(true);
            user = userRepository.save(user);
        }
        return user;
    }

    public User createUser(String fName, String mName, String lName, String eMail) {
        return userRepository.save(User.builder()
                .lName(lName)
                .mName(mName)
                .fName(fName)
                .eMail(eMail)
                .isActive(true)
                .build());
    }

    public Page<User> allUsersByPage(int pageNumber, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNumber - 1, pageSize));
    }

    public PaginationDto allUsersByPage(PaginationUserRequest paginationUserRequest) {
        return userRepository.getAllByParam(paginationUserRequest);
    }
}
