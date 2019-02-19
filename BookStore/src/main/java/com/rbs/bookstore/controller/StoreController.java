package com.rbs.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.bookstore.entity.Book;
import com.rbs.bookstore.service.StoreService;

@RestController
@RequestMapping(value = "/api")
public class StoreController {

	@Autowired
	private StoreService storeService;

	public void setBookService(StoreService bookService) {
		this.storeService = bookService;
	}

	@GetMapping("/bookList")
	public List<Book> getBooks() {
		List<Book> books = storeService.retrieveBooks();
		return books;
	}

	@GetMapping("/searchBook/{bookIsbn}")
	public Book getBookByIsbn(@PathVariable(name="bookIsbn")String bookIsbn) {
		return storeService.getBookByIsbn(bookIsbn);
	}

	@GetMapping("/findBooks/")
	public List<Book> getBookByAuthor(@RequestParam("bookAuthor")String bookAuthor) {
		return storeService.getBookByAuthor(bookAuthor);
	}

	@PostMapping("/saveBook")
	public ResponseEntity<String> saveBook(Book book){
		Boolean bookSave = storeService.saveBook(book);
		if(bookSave)
		{
			return new ResponseEntity<String>("Book saved Succesfully!", HttpStatus.ACCEPTED);
		}
		else
		{
			return new ResponseEntity<String>("Book already exist!", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@DeleteMapping("/removeBook/{bookIsbn}")
	public void deleteBook(@PathVariable(name="bookIsbn")String bookIsbn){
		storeService.deleteBook(bookIsbn);
		System.out.println("Book Deleted Successfully");
	}

	@PutMapping("/updateBook/{bookIsbn}")
	public void updateBook(@RequestBody Book book, @PathVariable(name="bookIsbn")String bookIsbn){
		Book existingBook = storeService.getBookByIsbn(bookIsbn);
		if(existingBook != null){
			storeService.updateBook(book);
		}

	}

}