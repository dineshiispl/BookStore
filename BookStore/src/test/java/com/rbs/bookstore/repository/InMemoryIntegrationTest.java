package com.rbs.bookstore.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.rbs.bookstore.entity.Book;
import com.rbs.bookstore.util.BookStoreJpaConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BookStoreJpaConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
@DirtiesContext
public class InMemoryIntegrationTest {
     
    @Resource
    private StoreRepository storeRepository;
    
    @Before
    public void storeBook()
    {
    	Book book1 = new Book("IN-DD7-5679-AE", new BigDecimal("200.75"), "Prakash Sundaram", "My Biography");
        storeRepository.save(book1);
        Book book2 = new Book("IN-DD5-5329-AE", new BigDecimal("575.90"), "Gabriel", "My Biography");
        storeRepository.save(book2);
        Book book3 = new Book("IN-DK7-7899-AE", new BigDecimal("595.55"), "David John", "My Biography");
        storeRepository.save(book3);
        Book book4 = new Book("IN-DD9-2279-AE", new BigDecimal("520.50"), "Gabriel", "My Expectations");
        storeRepository.save(book4);
    }
    
    @Test
    public void findByIsbn() {
        Book book1 = storeRepository.findByIsbn("IN-DK7-7899-AE");
        assertEquals("IN-DK7-7899-AE", book1.getIsbn());
    }
    
    @Test
    public void findByAuthor() {
        List<Book> booksList = storeRepository.findByAuthor("Gabriel");
        assertEquals(booksList.size(), 2);
    }
    
    @Test
    public void findAll() {
        List<Book> bookList = storeRepository.findAll();
        System.out.println("Book List size is ======================"+bookList.size());
        System.out.println(bookList);
        for (Book book : bookList) {
		System.out.println("Books........................"+book);	
		}
        assertEquals(bookList.size(), 4);
    }
    
    @Test
    public void submitBookTwice() {
    	
    	List<Book> myBooks = new ArrayList<Book>();
    	Book newBook = new Book("IN-DD9-2279-AF", new BigDecimal("520.50"), "Gabriel", "My Biography");
        myBooks.add(newBook);
        storeRepository.save(newBook);
        storeRepository.save(newBook);
        
        List<Book> bookList = storeRepository.findAll();
        assertEquals( bookList.size(), 5 );
    }
    
    @Test
    public void delete() {
    	
    	Book book = new Book();
    	book.setIsbn("IN-DD5-5329-AE");
        List<Book> bookList = storeRepository.findAll();
        storeRepository.delete(book);
        List<Book> refreshedBookList = storeRepository.findAll();
        assertFalse(refreshedBookList.size()==bookList.size());
    }
    
}