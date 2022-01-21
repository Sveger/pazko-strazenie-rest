package sk.upjs.strazenie_rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sk.upjs.strazenie.storage.DaoFactory;
import sk.upjs.strazenie.storage.EntityNotFoundException;
import sk.upjs.strazenie.storage.Osoba;
import sk.upjs.strazenie.storage.OsobaDao;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class OsobaDaoController {

	private OsobaDao osobaDao = DaoFactory.INSTANCE.getOsobaDao();
	
	@GetMapping
	public List<Osoba> getAll() {
		return osobaDao.getAll();
	}
	
	@PostMapping
	public ResponseEntity<Osoba> save(@RequestBody Osoba osoba){
		Osoba saved = osobaDao.save(osoba);
		if (osoba.getId() == null) {
			return new ResponseEntity<Osoba>(saved, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Osoba>(saved, HttpStatus.OK);
		}
	}
	
	@GetMapping("{name}")
	public Osoba getByMeno(@PathVariable("name") String userName) {
		try {
			return osobaDao.getByMeno(userName);
		} catch (EntityNotFoundException e) {
			
		}
		return null;
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") long id) {
		Osoba deleted = osobaDao.delete(id);
		if (deleted != null)
			return new ResponseEntity<>(HttpStatus.OK);
		else 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
