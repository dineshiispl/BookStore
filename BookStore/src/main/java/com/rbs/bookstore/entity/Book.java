package com.rbs.bookstore.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Book")
public class Book {

	@Id
	@Column(name="ISBN")
	private String isbn;

	@Column(name="COST")
	private BigDecimal cost;

	@Column(name="AUTHOR")
	private String author;

	@Column(name="GENRE")
	private String genre;
	
	public Book() {
		super();
	}

	public Book(String isbn, BigDecimal cost, String author, String genre) {
		super();
		this.isbn = isbn;
		this.cost = cost;
		this.author = author;
		this.genre = genre;
	}

	public Book(String isbn) {
		super();
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", cost=" + cost + ", author=" + author + ", genre=" + genre + "]";
	}
}

