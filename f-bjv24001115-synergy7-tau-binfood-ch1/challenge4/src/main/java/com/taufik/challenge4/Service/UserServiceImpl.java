package com.taufik.challenge4.Service;

import com.taufik.challenge4.Model.DTO.UserDTO;
import com.taufik.challenge4.Model.Entity.User;
import com.taufik.challenge4.Repository.UserRepository;
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
    public Page<UserDTO> getUsersWithPagination(Pageable pageable) {
        logger.info("Fetching all users with pagination");
        Page<User> userPage = userRepository.readAllUsers(pageable);
        if (userPage.isEmpty()) {
            logger.warn("No users found in the database");
        } else {
            logger.info("Users found: {}", userPage.getContent().size());
        }
        List<UserDTO> userDTOs = userPage.getContent().stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmailAddress(user.getEmailAddress());
            userDTO.setPassword(user.getPassword());
            return userDTO;
        }).collect(Collectors.toList());
        logger.info("Fetched {} users", userDTOs.size());
        return new PageImpl<>(userDTOs, pageable, userPage.getTotalElements());
    }

    @Override
    public UserDTO readUser(Long userId) {
        logger.info("Reading user with ID: {}", userId);
        User user = userRepository.readUser(userId);
        if (user == null) {
            logger.error("User with ID: {} not found", userId);
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmailAddress(user.getEmailAddress());
        userDTO.setPassword(user.getPassword());
        logger.info("User read: {}", userDTO.getUsername());
        return userDTO;
    }

    @Override
    @Transactional
    public String createUser(UserDTO userDTO) {
        try {
            logger.info("Creating user: {}", userDTO.getUsername());
            userRepository.createUser(userDTO.getUsername(), userDTO.getEmailAddress(), userDTO.getPassword());
            logger.debug("User created: {}", userDTO.getUsername());
            return "User created successfully";
        } catch (Exception e) {
            logger.error("Failed to create user: {}", e.getMessage());
            return "Failed to create user";
        }
    }

    @Override
    @Transactional
    public String updateUser(Long userId, UserDTO userDTO) {
        try {
            logger.info("Updating user with ID: {}", userId);
            userRepository.updateUser(userId, userDTO.getUsername(), userDTO.getEmailAddress(), userDTO.getPassword());
            logger.debug("User updated: {}", userDTO.getUsername());
            return "User updated successfully";
        } catch (Exception e) {
            logger.error("Failed to update user: {}", e.getMessage());
            return "Failed to update user";
        }
    }

    @Override
    @Transactional
    public String deleteUser(Long userId) {
        try {
            logger.info("Deleting user with ID: {}", userId);
            userRepository.deleteUser(userId);
            logger.debug("User deleted with ID: {}", userId);
            return "User deleted successfully";
        } catch (Exception e) {
            logger.error("Failed to delete user: {}", e.getMessage());
            return "Failed to delete user";
        }
    }
}
