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
import sk.upjs.strazenie.storage.Zviera;
import sk.upjs.strazenie.storage.ZvieraDao;
import sk.upjs.strazenie.storage.ZvieraXMajitel;

@CrossOrigin
@RestController
@RequestMapping("/animals")
public class ZvieraDaoController {
	
	private ZvieraDao zvieraDao = DaoFactory.INSTANCE.getZvieraDao();
	
	@GetMapping
	public List<ZvieraXMajitel> getVsetky() {
		return zvieraDao.getVsetky();
	}
	
	@GetMapping("{species}")
	public List<ZvieraXMajitel> getByDruhX(@PathVariable("species") String species){
		return zvieraDao.getByDruhX(species);
	}
	
	@GetMapping("/owner/{id}")
	public List<Zviera> getByMajitelId(@PathVariable("id") long ownerId){
		return zvieraDao.getByMajitelId(ownerId);
	}
	
	@PostMapping
	public ResponseEntity<Zviera> save(@RequestBody Zviera zviera){
		Zviera saved = zvieraDao.save(zviera);
		if (zviera.getId() == null) {
			return new ResponseEntity<Zviera>(saved, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Zviera>(saved, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") long id) {
		Zviera deleted = zvieraDao.delete(id);
		if (deleted != null)
			return new ResponseEntity<>(HttpStatus.OK);
		else 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
