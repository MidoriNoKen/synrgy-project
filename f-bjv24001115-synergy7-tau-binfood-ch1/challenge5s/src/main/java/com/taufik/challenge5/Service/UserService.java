package com.taufik.challenge5.Service;

import com.taufik.challenge5.Model.DTO.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDTO> list(Pageable pageable);

    UserDTO show(Long userId);

    UserDTO create(UserDTO userDTO);

    UserDTO update(Long userId, UserDTO userDTO);

    void delete(Long userId);
}
