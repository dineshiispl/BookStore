package com.rbs.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rbs.bookstore.entity.Book;

@Service
public interface StoreService {
 public List<Book> retrieveBooks();
  
 public Book getBookByIsbn(String isbn);

 public List<Book> getBookByAuthor(String author);
 
 public boolean saveBook(Book book);
  
 public void deleteBook(String isbn);
  
 public void updateBook(Book book);
}

