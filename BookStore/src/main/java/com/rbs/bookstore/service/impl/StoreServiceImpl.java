package com.rbs.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbs.bookstore.entity.Book;
import com.rbs.bookstore.repository.StoreRepository;
import com.rbs.bookstore.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreRepository bookRepository;

	public void setBookRepository(StoreRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> retrieveBooks() {
		List<Book> books = bookRepository.findAll();
		return books;
	}

	public Book getBookByIsbn(String isbn) {
		Book bookObj = bookRepository.findByIsbn(isbn);
		return bookObj;
	}

	public List<Book> getBookByAuthor(String author) {
		List<Book> bookObj = bookRepository.findByAuthor(author);
		return bookObj;
	}
	
	public boolean saveBook(Book book){
		Book bookExisting = bookRepository.findByIsbn(book.getIsbn());
		if(null==bookExisting)
		{
			bookRepository.save(book);
			return true;
		}
		return false;
	}

	public void updateBook(Book book) {
		bookRepository.save(book);
	}

	public void deleteBook(String isbn) {
		bookRepository.delete(new Book(isbn));
	}
}
