package org.example.sotre.repository;


import org.example.sotre.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ADMIN')")
@RepositoryRestResource(collectionResourceRel = "log",path = "log")
public interface LogRepository extends JpaRepository<Log,Integer> {

}
