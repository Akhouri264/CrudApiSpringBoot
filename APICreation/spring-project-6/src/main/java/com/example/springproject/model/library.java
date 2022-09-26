package com.example.springproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.logging.log4j.message.Message;
import org.jetbrains.annotations.NotNull;

import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

@Entity
@Table(name="libraryl", schema = "public")
@ApiIgnore
public class library {
	@Id
	@ApiModelProperty(notes = "bookid")
    @NotNull(value = "First Name cannot be null")
	private Integer bookid;

	@ApiModelProperty(notes = "bookname")
	private String bookname;
	@ApiModelProperty(notes = "bookauthor")
	private String bookauthor;
	@ApiModelProperty(notes = "bookcategory")
	private String bookcategory;
	@ApiModelProperty(notes = "bookdescriptions")
	private String bookdescriptions;
	public library() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getBookauthor() {
		return bookauthor;
	}
	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}
	public String getBookcategory() {
		return bookcategory;
	}
	public void setBookcategory(String bookcategory) {
		this.bookcategory = bookcategory;
	}
	public String getBookdescriptions() {
		return bookdescriptions;
	}
	public void setBookdescriptions(String bookdescriptions) {
		this.bookdescriptions = bookdescriptions;
	}
	@Override
	public String toString() {
		return "library [bookid=" + bookid + ", bookname=" + bookname + ", bookauthor=" + bookauthor + ", bookcategory="
				+ bookcategory + ", bookdescriptions=" + bookdescriptions + "]";
	}
	
}
