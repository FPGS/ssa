package es.gorka.edu.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.gorka.edu.connection.DataBase;
import es.gorka.edu.dto.AlumnoDTO;

@Service
public class NaranjaRepository {
	
	@Autowired
	DataBase db;
	
	public void insertNewNaranja(AlumnoDTO alumnoDto) {
		db.getNaranja().add(alumnoDto);
	}

	public ArrayList<AlumnoDTO> listAll() {
		return db.getNaranja();
	}
}