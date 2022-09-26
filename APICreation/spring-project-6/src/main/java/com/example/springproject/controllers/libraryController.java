package com.example.springproject.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//to disable security comment line number 14 to 17
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

import com.example.springproject.model.ExcelExporter;
import com.example.springproject.model.PdfConvertor;
//import com.example.springproject.model.Librarytrial;
import com.example.springproject.model.library;
import com.example.springproject.repository.libraryRepo;
import com.example.springproject.security.AuthRequest;
import com.example.springproject.security.AuthResponse;
//import com.example.springproject.security.JwtUtil;
//import com.example.springproject.services.UserService;
import com.example.springproject.services.libraryservice;
import com.sipios.springsearch.SpecificationImpl;
import com.sipios.springsearch.anotation.SearchSpec;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import springfox.documentation.annotations.ApiIgnore;
import io.swagger.annotations.*;
@RestController
@RequestMapping("/api/v1")
@Api(value = "Library Management System", description = "Operations in library Management System")
public class libraryController extends Exception {
	private static final long serialVersionUID = 1L;
	@Autowired
	private libraryservice libraryService;
	
	@ApiOperation(value = "View a list of available books", response = List.class)
	 @ApiResponses(value = {
		        @ApiResponse(code = 200, message = "Successfully retrieved book list"),
		        @ApiResponse(code = 401, message = "You are not authorized to view the book list"),
		        @ApiResponse(code = 403, message = "forbidden first login "),
		        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		    })
	@GetMapping("/library/all")
	public ResponseEntity<List<library>> getAll() {
		try {
			return libraryService.getAll();
		} catch (Exception e) {
			System.out.println("CtrilErro1" + e.getMessage());
			return null;
		}
	}

	@ApiOperation(value = "View a list of available with keyword", response = List.class)
	 @ApiResponses(value = {
		        @ApiResponse(code = 200, message = "Successfully retrieved book list"),
		        @ApiResponse(code = 401, message = "You are not authorized to view the book list"),
		        @ApiResponse(code = 403, message = "forbidden first login "),
		        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		    })
	@GetMapping("/library/all/key/{keyword}")
	public ResponseEntity<List<library>> getAllS(@PathVariable(name="keyword", required = false) String keyword) {
		try {
			return libraryService.listAll(keyword);
		} catch (Exception e) {
			System.out.println("SearchError::" + e.getMessage());
			return null;
		}
	}

	@ApiOperation(value = "View available books based on id", response = List.class)
	 @ApiResponses(value = {
		        @ApiResponse(code = 200, message = "Successfully retrieved book list"),
		        @ApiResponse(code = 401, message = "You are not authorized to view the book list"),
		        @ApiResponse(code = 403, message = "forbidden first login "),
		        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		    })
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

	@ApiOperation(value = "save and update new books", response = List.class)
	 @ApiResponses(value = {
		        @ApiResponse(code = 201, message = "Successfully created book"),
		        @ApiResponse(code = 401, message = "You are not authorized to view the book list"),
		        @ApiResponse(code = 403, message = "forbidden first login to save new book"),
		        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		    })
	@PostMapping("/library")
	public Object postbooks(@RequestBody library lib) {
		if (lib.getBookid() == null || lib.getBookid() < 1) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			try{
//				libraryService.saveBook(lib);
				return new ResponseEntity<Object>(libraryService.saveBook(lib),HttpStatus.CREATED);
			}catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@ApiOperation(value = "delete irrelevant books", response = List.class)
	 @ApiResponses(value = {
		        @ApiResponse(code = 200, message = "Successfully deleted book"),
		        @ApiResponse(code = 401, message = "You are not authorized to delete the book list"),
		        @ApiResponse(code = 403, message = "forbidden first login to delete book"),
		        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		    })
	@DeleteMapping("/library/id/{id}")
	public Object deleteBookById(@PathVariable(value = "id") int id) {
			ResponseEntity<library> l=getbookbyid(id);
			if(l.getStatusCodeValue()==404) {
			return new ResponseEntity<>("DATA IS UNAVAILABLE FOR GIVEN ID.",HttpStatus.BAD_REQUEST);
		}
		String message= libraryService.deleteBook(id);
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	

	@ApiOperation(value = "View available books based pagination and sorting", response = List.class)
	 @ApiResponses(value = {
		        @ApiResponse(code = 200, message = "Successfully retrieved book page"),
		        @ApiResponse(code = 401, message = "You are not authorized to view the book list"),
		        @ApiResponse(code = 403, message = "forbidden first login "),
		        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		    })
	@GetMapping("library/page/{offset}/{pageSize}/{choice}/{direction}")
	public  ResponseEntity<Page<library>> paginationSort(@PathVariable int offset,
			@PathVariable int pageSize,@PathVariable String choice,@PathVariable
			String direction) {
		ResponseEntity<Page<library>> pl= libraryService.bookPaginationSort(offset, pageSize,choice,direction);
		System.out.println(pl.getBody());
		if(pl.getStatusCodeValue()!=200) {
			System.out.println("offset="+offset+",pagesize="+pageSize+",choice="+choice);
			return null;
			}else {
				System.out.println("offset="+offset+",pagesize="+pageSize+",choice="+choice+" direction="+direction);
			return pl;
		}
	}

	@ApiOperation(value = "View available books based on pagination", response = List.class)
	 @ApiResponses(value = {
		        @ApiResponse(code = 200, message = "Successfully retrieved book list"),
		        @ApiResponse(code = 401, message = "You are not authorized to view the book list"),
		        @ApiResponse(code = 403, message = "forbidden first login "),
		        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		    })
	@GetMapping("library/page/{offset}/{pageSize}")
	public Object paginationLib(@PathVariable int offset, @PathVariable int pageSize) {
		return libraryService.bookPagination(offset, pageSize);
	}

	@ApiOperation(value = "Download book list", response = List.class)
	 @ApiResponses(value = {
		        @ApiResponse(code = 200, message = "Successfully download book list"),
		        @ApiResponse(code = 401, message = "You are not authorized to download the book list"),
		        @ApiResponse(code = 403, message = "forbidden first login "),
		        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		    })
	@GetMapping("/library/download")
    public Object exportToExcelAndPdf(HttpServletResponse response,@RequestParam String val) throws IOException {
		if(val.equalsIgnoreCase("excel")) 
		{
			response.setContentType("application/octet-stream");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	        int count=0;
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=Nikhil_" +(count++)+'_' +currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	         
	        List<library> listUsers =  libraryService.listAll();
	         
	        ExcelExporter excelExporter = new ExcelExporter(listUsers);
	         
	        excelExporter.export(response);
	        return "Success";
	     }
		else if(val.equalsIgnoreCase("pdf")) {
			response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	        List<library> listUsers = libraryService.listAll();
	        PdfConvertor exporter = new PdfConvertor(listUsers);
	        exporter.export(response);
	        return "pdf download started";
		}else {
			System.out.println("nul");
			return null;
		}
	}
	
}
