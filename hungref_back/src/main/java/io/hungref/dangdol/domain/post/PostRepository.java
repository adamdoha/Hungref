package io.hungref.dangdol.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends JpaRepository<Post,Long> {
    @Modifying @Transactional
    @Query("update Post p set p.views=p.views+1")
    void clicked();
}
