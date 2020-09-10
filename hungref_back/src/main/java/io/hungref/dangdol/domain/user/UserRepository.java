package io.hungref.dangdol.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    //이메일 중복 확인
    @Query("select u from User u where u.email=:email")
    List<User> checkByEmail(@Param("email") String email);

    //로그인
    @Query("select u from User u where u.email=:email and u.password=:password")
    User signIn(@Param("email") String email, @Param("password") String password);

    @Query("select u from User u where u.email=:email")
    User findByEmail(@Param("email") String email);
}
