package com.example.springproject.services;

import com.example.springproject.model.library;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface libraryServiceInterface {
	public abstract ResponseEntity<List<library>> getAll();
	public abstract ResponseEntity<library> getBookById(int id);
	public abstract Object saveBook(library lib);
	public abstract Object deleteBook(int id);
	public abstract ResponseEntity<Page<library>> bookPaginationSort(int offset,int pageSize,String choice,String direction);
	public abstract ResponseEntity<List<library>> listAll(String keyword);
	public abstract Page<library> bookPagination(int offset, int pageSize);
}
