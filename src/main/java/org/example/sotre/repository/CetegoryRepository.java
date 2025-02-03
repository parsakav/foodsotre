package org.example.sotre.repository;

import org.example.sotre.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;


@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CetegoryRepository extends JpaRepository<Category,Integer> {

    Category findCategoryByName(String name);

    @RestResource(exported = false)
    @Override
    void deleteById(Integer integer);
}
