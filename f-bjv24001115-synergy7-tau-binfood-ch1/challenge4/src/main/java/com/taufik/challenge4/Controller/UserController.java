package com.taufik.challenge4.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.taufik.challenge4.Model.DTO.UserDTO;
import com.taufik.challenge4.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userService.getUsersWithPagination(pageable);
    }

    @GetMapping("/{id}")
    public UserDTO readUser(@PathVariable Long id) {
        return userService.readUser(id);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        String message = userService.createUser(userDTO);
        return ResponseEntity.status(message.contains("successfully") ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST)
                .body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        String message = userService.updateUser(id, userDTO);
        return ResponseEntity.status(message.contains("successfully") ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String message = userService.deleteUser(id);
        return ResponseEntity.status(message.contains("successfully") ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(message);
    }
}
