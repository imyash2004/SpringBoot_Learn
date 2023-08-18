package com.api.book.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.dao.BookRepository;
import com.api.book.entities.Book;

@Component
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	//private static List<Book> l=new ArrayList<>();
	
//	static {
//		l.add(new Book(12,"java complete","xyz"));
//		l.add(new Book(23,"java reference","abc"));
//		l.add(new Book(24,"boot reference","pqr"));
//		
//		
//		
//		
//	}
	public List<Book> getAllBooks(){
		List<Book> l=(List<Book>) this.bookRepository.findAll();
		
		
		return l;
	}
	public Book getBookById(int id) {
		Book book=null;
		try {
//			book=l.stream().filter(e->e.getId()==id).findFirst().get();
			Book b=this.bookRepository.findById(id);
			return b;
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	return book;
	}
	
	public Book addBook(Book b) {
		Book b1=this.bookRepository.save(b);
		return b1;
	}
	public void deleteBook(int bid) {
//		Book book=l.stream().filter(e->e.getId()==bid).findFirst().get();
//		l.remove(book);
		
//		l.stream().filter(b->{
//			if(b.getId()!=bid)
//			return true;
//			else 
//			return false;
//		}).collect(Collectors.toList());
		
		bookRepository.deleteById(bid);
		
	}
	public void updateBook(Book book,int bid) {
//		l=l.stream().map(b->{
//			if(b.getId()==bid) {
//				b.setTitle(book.getTitle());
//				b.setAuthor(book.getAuthor());
//			}
//			return b;
//		}).collect(Collectors.toList());
		book.setId(bid);
		bookRepository.save(book);
		
	}
}
