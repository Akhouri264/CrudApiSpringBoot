package com.example.springproject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springproject.model.library;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@EnableJpaRepositories
@Repository
//@RepositoryRestResource
public interface libraryRepo extends JpaSpecificationExecutor<library>, JpaRepository<library, Integer>{

//	List<library> search(String keyword);
//
//    @Query("FROM Book b WHERE b.title LIKE %:searchText% OR b.author LIKE %:searchText% OR b.language LIKE %:searchText% OR b.genre LIKE %:searchText% ORDER BY b.price ASC")
//    Page<library> findAllBooks(Pageable pageable, @Param("searchText") String searchText);
	@Query("SELECT p FROM library p WHERE p.bookname=:keyword"
            + " OR p.bookcategory =:keyword"
            + " OR p.bookdescriptions =:keyword"
			)
    public List<library> search(@Param("keyword") String keyword);
}

