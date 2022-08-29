package com.example.springproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="libraryl", schema = "public")
public class library {
	@Id
	private Integer bookid;
	private String bookname;
	private String bookauthor;
	private String bookcategory;
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
