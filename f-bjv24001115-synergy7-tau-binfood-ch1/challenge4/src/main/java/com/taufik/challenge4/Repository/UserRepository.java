package com.taufik.challenge4.Repository;

import com.taufik.challenge4.Model.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Query(value = "SELECT * FROM read_all_users()", nativeQuery = true)
    Page<User> readAllUsers(Pageable pageable);

    @Query(value = "SELECT * FROM read_user(:user_id)", nativeQuery = true)
    User readUser(@Param("user_id") Long userId);

    @Query(value = "SELECT create_user(:username, :email_address, :password)", nativeQuery = true)
    void createUser(@Param("username") String username, @Param("email_address") String emailAddress,
            @Param("password") String password);

    @Query(value = "SELECT update_user(:user_id, :username, :email_address, :password)", nativeQuery = true)
    void updateUser(@Param("user_id") Long userId, @Param("username") String username,
            @Param("email_address") String emailAddress, @Param("password") String password);

    @Query(value = "SELECT delete_user(:user_id)", nativeQuery = true)
    void deleteUser(@Param("user_id") Long userId);
}
