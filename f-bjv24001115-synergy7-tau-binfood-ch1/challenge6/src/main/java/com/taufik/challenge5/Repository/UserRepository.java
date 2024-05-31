package com.taufik.challenge5.Repository;

import com.taufik.challenge5.Model.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

        @Query(value = "SELECT * FROM all_users()", nativeQuery = true)
        Page<User> list(Pageable pageable);

        @Query(value = "SELECT * FROM show_user(:id)", nativeQuery = true)
        User read(@Param("id") Long id);

        @Query(value = "SELECT create_user(:username, :email, :password)", nativeQuery = true)
        void create(@Param("username") String username, @Param("email") String emailAddress,
                        @Param("password") String password);

        @Query(value = "SELECT update_user(:id, :username, :email, :password)", nativeQuery = true)
        void update(@Param("id") Long id, @Param("username") String username,
                        @Param("email") String emailAddress, @Param("password") String password);

        @Query(value = "SELECT delete_user(:id)", nativeQuery = true)
        void delete(@Param("id") Long id);
}
