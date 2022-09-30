package com.example.carmodel.repository;

import com.example.carmodel.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    Model findOneById(long id);

//    Model findOnyByName(String name);

    @Query(value = "SELECT m FROM Model m WHERE " +
            "LOWER(m.name) LIKE LOWER(CONCAT('%', :name,'%')) " +
            "AND LOWER(m.category) LIKE LOWER(CONCAT('%', :category,'%'))")
    List<Model> findByNameAndCategory(@Param("name") String name, @Param("category") String category);

    @Query(value = "SELECT m FROM Model m WHERE " +
            "LOWER(m.name) LIKE LOWER(CONCAT('%', :name,'%')) " +
            "AND LOWER(m.category) LIKE LOWER(CONCAT('%', :category,'%'))" +
            "AND m.year = :year")
    List<Model> findByNameAndCategoryAndYear(@Param("name") String name, @Param("category") String category, @Param("year") Integer year);

    //    List<Model> findByYear(long year);
//
//    List<Model> findByCategory(String category);
}