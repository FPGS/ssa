package es.gorka.edu.model;

import java.sql.Date;
import java.util.List;

public class Author {

	private String nameOfAuthor;
	private Date dateOfBirthFromAuthor;
	
	public String getNameOfAuthor() {
		return nameOfAuthor;
	}
	public void setNameOfAuthor(String nameOfAuthor) {
		this.nameOfAuthor = nameOfAuthor;
	}
	
	public Date getDateOfBirthFromAuthor() {
		return dateOfBirthFromAuthor;
	}
	public void setDateOfBirthFromAuthor(Date dateOfBirthFromAuthor) {
		this.dateOfBirthFromAuthor = dateOfBirthFromAuthor;
	}

}
