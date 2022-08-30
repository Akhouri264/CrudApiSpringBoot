package com.example.springproject.controllers;

import java.util.List;

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
import com.sipios.springsearch.SpecificationImpl;
import com.sipios.springsearch.anotation.SearchSpec;

@RestController
//@RequestMapping("/api/v1")
public class libraryController extends Exception {
	private static final long serialVersionUID = 1L;
//	@Autowired 
//	private AuthenticationManager authenticationManager;
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
		try {
			return libraryService.getAll();
		} catch (Exception e) {
			System.out.println("CtrilErro1" + e.getMessage());
			return null;
		}
	}
	@GetMapping("/library/all/key/{keyword}")
	public ResponseEntity<List<library>> getAllS(@PathVariable(name="keyword", required = false) String keyword) {
		try {
			return libraryService.listAll(keyword);
		} catch (Exception e) {
			System.out.println("SearchError::" + e.getMessage());
			return null;
		}
	}
	@GetMapping(path="/library/api/{id}")
	public ResponseEntity<library> getbookbyid(@PathVariable(value = "id") Integer id) {
		try {
		return libraryService.getBookById(id);
		}
		catch (Exception e) {
			System.out.println("getbook_id::"+e.getMessage());
			return null;
		}
	}

	@PostMapping("/library")
	public Object postbooks(@RequestBody library lib) {
		if (lib.getBookid() == null || lib.getBookid() < 1) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			try{
				return libraryService.saveBook(lib);
			}catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@DeleteMapping("/library/id/{id}")
	public Object deleteBookById(@PathVariable(value = "id") int id) {
		String message= libraryService.deleteBook(id);
		if(!message.equals("false")) {
			return new ResponseEntity<>(message,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("library/page/{offset}/{pageSize}/{choice}/{direction}")
	public  ResponseEntity<Page<library>> paginationSort(@PathVariable String offset,
			@PathVariable String pageSize,@PathVariable String choice,@PathVariable
			String direction) {
		int Intoffset=Integer.parseInt(offset);
		int IntpageSize=Integer.parseInt(pageSize);
		ResponseEntity<Page<library>> pl= libraryService.bookPaginationSort(Intoffset, IntpageSize,choice,direction);
		if(pl.getStatusCode()!=HttpStatus.OK) {
			System.out.println("offset="+offset+",pagesize="+pageSize+",choice="+choice);
			return null;
			}else {
				System.out.println("offset="+offset+",pagesize="+pageSize+",choice="+choice);
			return pl;
		}
	}
	//try to know about slice and page 
//	@GetMapping("library/pages/{keyword}")
//	public  Object paginationSearch(Pageable pageable,@PathVariable String keyword) {
//		return libraryService.findAllLibrBook(pageable,keyword);
//	}
	
	@GetMapping("library/page/{offset}/{pageSize}")
	public Object paginationLib(@PathVariable int offset, @PathVariable int pageSize) {
		return libraryService.bookPagination(offset, pageSize);
	}
	
	
	//to produce bug
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
	//to produce bug
	
}
