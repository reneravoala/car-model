package com.example.carmodel.repository;

import com.example.carmodel.entity.Make;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MakeRepository extends JpaRepository<Make, Long> {
    Make findByName(String name);

    Make findById(long id);
}