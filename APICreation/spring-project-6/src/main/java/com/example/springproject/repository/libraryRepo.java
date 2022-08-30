package com.example.springproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.springproject.model.library;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@EnableJpaRepositories
@Repository
@RepositoryRestResource
public interface libraryRepo extends JpaSpecificationExecutor<library>, JpaRepository<library, Integer>{

	@Query("SELECT p FROM library p WHERE p.bookname=:keyword"
            + " OR p.bookcategory =:keyword"
            + " OR p.bookdescriptions =:keyword"
            + " OR p.bookauthor =:keyword"
			)
    public List<library> search(@Param("keyword") String keyword);
}

