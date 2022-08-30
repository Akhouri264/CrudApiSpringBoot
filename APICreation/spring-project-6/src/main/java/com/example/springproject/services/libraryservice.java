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
import com.example.springproject.repository.libraryRepo;

import org.springframework.http.ResponseEntity;
@Service
public class libraryservice implements libraryServiceInterface{
	@Autowired
	private libraryRepo librare;

	@Autowired
	private SearchRepo searchTrial;

	@Override
	public ResponseEntity<List<library>> getAll()
	{
		try {
			return new ResponseEntity<>(librare.findAll(),HttpStatus.OK);
		}
		catch(Exception e){
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	@Override
	public ResponseEntity<List<library>> listAll(String keyword) {
        if (librare.search(keyword).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
        	return new ResponseEntity<>(librare.search(keyword),HttpStatus.OK);
        }
        }
	@Override
	public Object saveBook(library lib) {
			try {
				return librare.save(lib);
			}catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
				// TODO: handle exception
			}
	}
	
	@Override
	public ResponseEntity<library> getBookById(int id){
		if(librare.findById(id).isEmpty()) {
			return new ResponseEntity(librare.findById(id),HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity(librare.findById(id),HttpStatus.OK);
		}
	}
	
	@Override
	public String deleteBook(int id) {
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
			return "false";
		}}
	}
	@Override
	public ResponseEntity<Page<library>> bookPaginationSort(int offset,int pageSize,String choice,String direction){
		try {
		return new ResponseEntity<>(librare.findAll(
				PageRequest.of(
						offset, pageSize,direction.equalsIgnoreCase("asc") ? Sort.by(choice).ascending()
								: Sort.by(choice).descending())), HttpStatus.OK);
		}
		catch (Exception e) {
			System.out.println("sorting Error::"+e.getMessage());
			return null;
		}
    }
	@Override
	public Page<library> bookPagination(int offset,int pageSize){
		try {
		return librare.findAll(PageRequest.of(offset, pageSize));
		}
		catch (Exception e) {
			System.out.println("sorting Error::"+e.getMessage());
			return null;
		}
    }

	
	
	//used to produce the bug
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
		}
	}
	//*end
	
}
	
