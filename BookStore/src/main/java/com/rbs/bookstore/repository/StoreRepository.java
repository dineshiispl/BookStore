package com.rbs.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rbs.bookstore.entity.Book;
 
@Repository
public interface StoreRepository extends JpaRepository<Book,Long>{
 
	@Query("SELECT t FROM Book t WHERE t.isbn = ?1")
	Book findByIsbn(String isbn);
	
	@Query("SELECT t FROM Book t WHERE t.author = ?1")
	List<Book> findByAuthor(String author);
}

