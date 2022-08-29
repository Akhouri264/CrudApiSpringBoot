package com.example.springproject.services;

import com.example.springproject.model.library;
import java.util.*;

import org.springframework.data.domain.Page;

public interface libraryServiceInterface {
	public abstract List<library> getAll();
	public abstract library getBookById(Integer id);
	public abstract Object saveBook(library lib);
	public abstract Object deleteBook(int id);
	public Page<library> bookPaginationSort(int offset,int pageSize,String choice);
}
