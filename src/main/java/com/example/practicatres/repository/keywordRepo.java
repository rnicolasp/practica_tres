package com.example.practicatres.repository;
import com.example.practicatres.models.entities.keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface keywordRepo extends JpaRepository<keyword, Integer> {

}
