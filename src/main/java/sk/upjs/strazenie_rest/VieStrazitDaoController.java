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
import sk.upjs.strazenie.storage.Strazca;
import sk.upjs.strazenie.storage.VieStrazit;
import sk.upjs.strazenie.storage.VieStrazitDao;

@CrossOrigin
@RestController
@RequestMapping("/watch")
public class VieStrazitDaoController {

	private VieStrazitDao vieStrazitDao = DaoFactory.INSTANCE.getVieStrazitDao();
	
	@GetMapping("{id}")
	public List<Strazca> getByDruhId(@PathVariable("id") long id) {
		return vieStrazitDao.getByDruhId(id);
	}
	
	@GetMapping("/user/{id}")
	public List<VieStrazit> getPonukaByOsobaId(@PathVariable("id") long id) {
		return vieStrazitDao.getPonukaByOsobaId(id);
	}
	
	@PostMapping
	public ResponseEntity<VieStrazit> save(@RequestBody VieStrazit vieStrazit){
		VieStrazit saved = vieStrazitDao.save(vieStrazit);
		if (vieStrazit.getId() == null) {
			return new ResponseEntity<>(saved, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(saved, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") long id) {
		boolean deleted = vieStrazitDao.delete(id);
		if (deleted == true)
			return new ResponseEntity<>(HttpStatus.OK);
		else 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
