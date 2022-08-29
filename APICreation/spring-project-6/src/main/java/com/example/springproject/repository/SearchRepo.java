package com.example.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springproject.model.Librarytrial;
import com.example.springproject.model.library;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.springproject.model.library;

//@Repository
//public interface SearchRepo extends PagingAndSortingRepository<library, Integer>{
////
//    @Query("FROM library b WHERE "
//    		+ "b.book_author LIKE %:searchText% OR b.bookName LIKE "
//    		+ "%:searchText% OR b.book_category LIKE %:searchText% ORDER BY b.bookId ASC")
//	Page<library> findAllBooks(Pageable pageable, @PathVariable("searchText") String searchText);
//}
@Repository
public interface SearchRepo extends JpaRepository<Librarytrial, Integer>{
//
//    @Query("FROM library b WHERE  b.bookauthor LIKE %:searchText% OR b.bookdescriptions LIKE %:searchText% OR b.category LIKE %:searchText% ORDER BY b.bookid ASC")
//    Page<library> findAllBooks(Pageable pageable, @Param("searchText") String searchText);
}