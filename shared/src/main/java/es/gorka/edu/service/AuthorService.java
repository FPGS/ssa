package es.gorka.edu.service;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.gorka.edu.dto.AuthorDTO;
import es.gorka.edu.model.Author;
import es.gorka.edu.repository.AuthorRepository;

@Service
public class AuthorService {

	private static final Logger logger = LogManager.getLogger(AuthorService.class.getName());
	
	@Autowired
	AuthorRepository aRepo;
	
	public boolean insert(AuthorDTO authorDTO) {
		aRepo.insert(authorDTO);
		logger.debug("simulando insercion de un Autor");
		return true;
	}

	public List<Author> listAllAuthors(Author author) {
		return aRepo.findAuthors(author);
	}
	
}