package com.taufik.challenge5.Service.Impl;

import com.taufik.challenge5.Model.DTO.UserDTO;
import com.taufik.challenge5.Model.Entity.User;
import com.taufik.challenge5.Repository.UserRepository;
import com.taufik.challenge5.Service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserDTO> list(Pageable pageable) {
        logger.info("Fetching all users with pagination");
        Page<User> userPage = userRepository.list(pageable);

        if (userPage.isEmpty())
            logger.warn("No users found in the database");
        else
            logger.info("Users found: {}", userPage.getContent().size());

        List<UserDTO> userDTOs = userPage.getContent().stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            return userDTO;
        }).collect(Collectors.toList());

        logger.info("Fetched {} users", userDTOs.size());
        return new PageImpl<>(userDTOs, pageable, userPage.getTotalElements());
    }

    @Override
    public UserDTO show(Long userId) {
        logger.info("Reading user with ID: {}", userId);

        User user = userRepository.read(userId);
        if (user == null) {
            logger.error("User with ID: {} not found", userId);
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());

        logger.info("User read: {}", userDTO.getUsername());
        return userDTO;
    }

    @Override
    @Transactional
    public UserDTO create(UserDTO userDTO) {
        try {
            logger.info("Creating user: {}", userDTO.getUsername());

            userRepository.create(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());

            logger.debug("User created: {}", userDTO.getUsername());
            return userDTO;
        } catch (Exception e) {
            logger.error("Failed to create user: {}", e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public UserDTO update(Long userId, UserDTO userDTO) {
        try {
            logger.info("Updating user with ID: {}", userId);

            userRepository.update(userId, userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());

            logger.debug("User updated: {}", userDTO.getUsername());
            return userDTO;
        } catch (Exception e) {
            logger.error("Failed to update user: {}", e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        try {
            logger.info("Deleting user with ID: {}", userId);
            userRepository.delete(userId);
            logger.debug("User deleted with ID: {}", userId);
        } catch (Exception e) {
            logger.error("Failed to delete user: {}", e.getMessage());
        }
    }
}
