package es.gorka.edu.dto;

import org.springframework.stereotype.Component;

@Component
public class BookDTO implements IEntityDTO {

	private String isbn;
	private String nameOfAuthor;
	private String titleOfBook;
	
	public String getTitleOfBook() {
		return titleOfBook;
	}
	public void setTitleOfBook(String titleOfBook) {
		this.titleOfBook = titleOfBook;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getNameOfAuthor() {
		return nameOfAuthor;
	}
	public void setNameOfAuthor(String nameOfAuthor) {
		this.nameOfAuthor = nameOfAuthor;
	}

}
