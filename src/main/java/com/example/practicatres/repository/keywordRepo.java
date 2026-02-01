package com.example.practicatres.repository;
import com.example.practicatres.models.entities.keyword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface keywordRepo extends JpaRepository<keyword, Integer> {
    @Query("SELECT k FROM keyword k WHERE LOWER(k.keyword_name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<keyword> findByKeywordNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);
}
