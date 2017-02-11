package es.gorka.edu.repository;

import es.gorka.edu.dto.AuthorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.gorka.edu.assembler.Assembler;
import es.gorka.edu.connection.AbstractConnectionManager;

import es.gorka.edu.model.Author;

@Service
public class AuthorRepository {
	
	private static final Logger logger = LogManager.getLogger(AuthorRepository.class.getName());
	
	@Autowired
	private Assembler<AuthorDTO, Author> asesemblerAuthor;
	
	@Autowired
	private AbstractConnectionManager conManager;
	
	public void insert(AuthorDTO authorDTO) {
	
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 
		 Author author = new Author();
		 asesemblerAuthor.toEntity(authorDTO, author);
		 
		 try {
			 	connection = conManager.open();
			 	
			 	preparedStatement = connection.prepareStatement("INSERT INTO AuthorTable (nameOfAuthor, dateOfBirth) VALUES (?, ?)");
			 	
			 	preparedStatement.setString(1, author.getName());
			 	preparedStatement.setDate(2, author.getDateOfBirth());
			 	preparedStatement.executeUpdate();
		 
		 	logger.debug("INSERT INTO AuthorTable (nameOfAuthor, dateOfBirth) VALUES (?, ?)");
		 
		 	} catch (Exception e) {
		 			logger.error(e);
		 			throw new RuntimeException(e);
		 	} finally {
		 			conManager.close(preparedStatement);
		 			conManager.close(connection);
		 	}
	}
}