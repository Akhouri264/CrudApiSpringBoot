package com.example.springproject.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springproject.model.Librarytrial;
import com.example.springproject.model.library;
import com.example.springproject.repository.libraryRepo;
import com.example.springproject.security.AuthRequest;
import com.example.springproject.security.AuthResponse;
//import com.example.springproject.services.UserService;
import com.example.springproject.services.libraryservice;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;

import org.springframework.http.HttpHeaders;
//import com.sipios.springsearch.SpecificationImpl;
//import com.sipios.springsearch.anotation.SearchSpec;

//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "*")
@RestController
//@RequestMapping("/api/v1")
public class libraryController extends Exception {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	@Autowired 
//	private AuthenticationManager authenticationManager;
//	
	@Autowired
	private libraryservice libraryService;

//	@Autowired
//	private JwtUtil jwtUtil;
//	@Autowired 
//	private UserService userService; 
//	
//	@GetMapping("/authenticate")
//    public AuthResponse authenticate(@RequestBody AuthRequest jwtRequest) throws Exception{
//
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            jwtRequest.getUsername(),
//                            jwtRequest.getPassword()
//                    )
//            );
//        } catch (Exception e) {
//            System.out.println(jwtRequest.getUsername());
//            System.out.println(jwtRequest.getPassword());
//        	System.out.println(e.getMessage());
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//        
//        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
//
//        final String token =
//                jwtUtil.generateToken(userDetails);
//
//        return  new AuthResponse(token);
//    }
	@GetMapping("/library/all")
	public ResponseEntity<List<library>> getAll() {
		List<library> BooksList=libraryService.getAll();
		if(BooksList.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(BooksList);
	}
	@GetMapping("/library/all/key/{keyword}")
	public List<library> getAllS(@PathVariable(name="keyword", required = false) String keyword) {
		try {
			System.out.println("getalls::"+keyword);
			return libraryService.listAll(keyword);
		} catch (Exception e) {
			System.out.println("SearchError::" + e.getMessage());
			return null;
		}
	}
	@GetMapping(path="/library/api/{id}")
	public ResponseEntity<library> getbookbyid(@PathVariable(value = "id") Integer id,HttpServletResponse hr) {
		library book=libraryService.getBookById(id);
		if(book==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(book);
		
	}

	@PostMapping("/library")
	public ResponseEntity<library> postbooks(@RequestBody library lib) {
		library libbook=libraryService.saveBook(lib);
		  HttpHeaders responseHeaders = new HttpHeaders();
		if (lib.getBookid() == null || lib.getBookid() < 1) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		} else {
		    return ResponseEntity.ok().headers(responseHeaders).body(libbook);
		}
	}

	@DeleteMapping("/library/id/{id}")
	public String deleteBookById(@PathVariable(value = "id") int id) {
//		try {
//		System.out.println();
		ResponseEntity<library> l=getbookbyid(id, null);
		if(!l.hasBody()) {
			return "id not found so not able to delete";
		}
		else {
			libraryService.deleteBook(id);
			return "successfully deleted";
		}
//			return  ResponseEntity.status(HttpStatus.OK).build();
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		}
	}

//	@GetMapping("{searchText}")
//	public Page<library> SearchAll(Pageable pageable,@PathVariable String searchText) {
//		try {
//			if(libraryService.searchAll(pageable, searchText)==null) {
//				System.out.println( "False::"+HttpStatus.BAD_REQUEST);
//				return null;
//			}else {
//				return libraryService.searchAll(pageable, searchText);
//			}
//		}catch (Exception e) {
//			System.out.println("Error/Exception Found::"+e.getMessage());
//		}
//		return null;
//	}
//	@GetMapping("/library/all/{field}")
//	public List<library> Searchsort(@PathVariable String field) {
//		System.out.println("field is :::"+field);
//		return  libraryService.sorting(field);
//	}
	
	@GetMapping("library/page/{offset}/{pageSize}/{choice}")
	public Page<library> paginationSortWithAttribute(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String choice) {
		return libraryService.bookPaginationSort(offset, pageSize,choice);
		//		Page<library> sortAttribute= libraryService.bookPaginationSort(offset, pageSize,choice);
//		if(sortAttribute!=null) {
//			return ResponseEntity.status(HttpStatus.OK).body(sortAttribute);
//		}else {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		}
	}
	@GetMapping("library/page/{offset}/{pageSize}")
	public Object paginationLib(@PathVariable int offset, @PathVariable int pageSize) {
		return libraryService.bookPagination(offset, pageSize);
	}
	@GetMapping("library1/page/{offset}/{pageSize}/{choice}")
	public Object paginationLi(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String choice) {
		try {
			return libraryService.bookPaginationtrial(offset, pageSize,choice);
		}catch (Exception e) {
			System.out.println("paginationLi"+e.getMessage());
			return null;
			// TODO: handle exception
		}
	}
	@GetMapping("/libraryl/all")
	public List<Librarytrial> getAlltrial() {
		try {
			return libraryService.getAlltrial();
		} catch (Exception e) {
			System.out.println("CtrilErro1" + e.getMessage());
			return null;
		}
	}
	
	
}
