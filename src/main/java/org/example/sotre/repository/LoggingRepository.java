package org.example.sotre.repository;

import org.example.sotre.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingRepository extends JpaRepository<Log,Integer> {
}
