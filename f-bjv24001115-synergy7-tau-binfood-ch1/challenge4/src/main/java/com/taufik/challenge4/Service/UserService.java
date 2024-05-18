package com.taufik.challenge4.Service;

import com.taufik.challenge4.Model.DTO.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDTO> getUsersWithPagination(Pageable pageable);

    UserDTO readUser(Long userId);

    String createUser(UserDTO userDTO);

    String updateUser(Long userId, UserDTO userDTO);

    String deleteUser(Long userId);
}
