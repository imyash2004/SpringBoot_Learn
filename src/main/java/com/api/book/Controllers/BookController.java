package com.api.book.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.entities.Book;
import com.api.book.services.BookService;

import jakarta.annotation.PostConstruct;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;
	
	
	
	//@RequestMapping(value="books",method=RequestMethod.GET)
	//@ResponseBody 
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBook() {
		
		
		List<Book>list=bookService.getAllBooks();
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
//		Book book=new Book();
//		book.setId(121);
//		book.setAuthor("Yash Agarwal");
//		book.setTitle("Java Complex References");
//		
		
		return ResponseEntity.status(HttpStatus.OK  ).body(list);
	}
	
	@GetMapping("/books/{id}")
	//@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "NOT_Found")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book book= bookService.getBookById(id);
		if(book ==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
		
	}
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) { 
		Book b=null;
			try {
				b=	this.bookService.addBook(book);
				return ResponseEntity.of(Optional.of(b));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		
	}
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId")int bookId ) {
		//System.out.println("deleted");
		try {
			this.bookService.deleteBook(bookId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookId")  int bookId) {
		
		try {
			this.bookService.updateBook(book, bookId);
			return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}
}
