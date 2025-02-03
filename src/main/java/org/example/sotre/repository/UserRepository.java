package org.example.sotre.repository;

import org.example.sotre.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User,Integer> {


    User findUserByEmail(String email);

  /*  @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);*/
  @Modifying
  @Transactional
  @RestResource(exported = false)

  @Query("update User u set u.password =:password where u.email =:email")
    void resetPassword(@Param("email") String email, @Param("password") String password);
}
