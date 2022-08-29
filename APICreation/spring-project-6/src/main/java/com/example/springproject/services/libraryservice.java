package com.example.springproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Sort;

import com.example.springproject.model.Librarytrial;
import com.example.springproject.model.library;
import com.example.springproject.repository.SearchRepo;
//import com.example.springproject.repository.SearchRepo;
import com.example.springproject.repository.libraryRepo;

import org.springframework.http.ResponseEntity;
@Service
public class libraryservice implements libraryServiceInterface{
//},SearchInterface<library> {
	@Autowired
	private libraryRepo librare;

	@Autowired
	private SearchRepo searchTrial;
//	@Autowired
//	private library Ilibr;
	@Override
	public List<library>getAll()
	{
		try {
			return (List<library>) librare.findAll();
		}
		catch(Exception e){
			System.out.println("error1"+e.getMessage());
			return null;
		}
	}
	public List<library> listAll(String keyword) {
        if (keyword != null) {
            return librare.search(keyword);
        }else {
        	System.out.println("keyword::"+keyword);
        return librare.findAll();
        }
        }
	@Override
	public library saveBook(library lib) {
		try{
			return librare.save(lib);
		}
		catch(Exception e) {
			System.out.println("error2::"+e.getMessage());
			return null;
		}
	}
	
	@Override
	public library getBookById(Integer id){
		try {
			return librare.findById((Integer) id).orElse(null);
		}
		catch (Exception e) {
			System.err.println("getbook::"+e.getMessage());
			return null;
		}
	}
	
	@Override
	public Object deleteBook(int id) {
		if(id<=0)
		{
			return "cannot delete with the given id";
		}
		else {
		try
		{
			librare.deleteById(id);
			return "item deleted successfully";
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			return false;
		}}
	}
//	@Override
//	public Object findAll(int pageNumber, int pageSize, String sortBy, String sortDir){
//		return ResponseEntity<>(Ilib.findAll(
//				PageRequest.of(
//						pageNumber, pageSize,
//						sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
//				)
//		), HttpStatus.OK);
//	}
//	@Autowired
//	private SearchRepo seo;
//	@Override
//	public Page<library> searchAll(Pageable pageable, String searchText) {
//		// TODO Auto-generated method stub
//		try {
//			return searchTrial.findAllBooks(pageable, searchText);
//		}
//		catch(Exception e) {
//			System.out.println("FindAll Exception::"+e.getMessage());
//			return null;
//		}
//	}
//	public List<library> sorting(String field) {
//		try {
//			return librare.findAll(Sort.by(Sort.Direction.ASC,field));
//		}catch (Exception e) {
//			System.out.println("field is :"+field);
//			System.out.println("sorting Exception:"
//			//+field
//					+"::"+e.getMessage());
//			return null;
//		}
//	}
	
	public Page<library> bookPaginationSort(int offset,int pageSize,String choice){
		try {
		return librare.findAll(PageRequest.of(offset, pageSize).withSort(Sort.Direction.ASC,choice));
		}
		catch (Exception e) {
			System.out.println("sorting Error::"+e.getMessage());
			return null;
		}
    }
	public Page<library> bookPagination(int offset,int pageSize){
		try {
		return librare.findAll(PageRequest.of(offset, pageSize));
		}
		catch (Exception e) {
			System.out.println("sorting Error::"+e.getMessage());
			return null;
		}
    }

	public Page<Librarytrial> bookPaginationtrial(int offset,int pageSize,String choice ){
		try {
		return searchTrial.findAll(PageRequest.of(offset, pageSize).withSort(Sort.Direction.ASC, choice));
		}
		catch (Exception e) {
			System.out.println("pagination Error::"+e.getMessage());
			return null;
		}
    }
	public List<Librarytrial>getAlltrial()
	{
		try {
			return searchTrial.findAll();
		}catch (Exception e) {
			System.out.println("trialError::"+e.getMessage());
			return null;
			// TODO: handle exception
		}
		
	}
}
	
