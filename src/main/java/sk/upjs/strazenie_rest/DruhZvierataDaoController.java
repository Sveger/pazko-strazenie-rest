package sk.upjs.strazenie_rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sk.upjs.strazenie.storage.DaoFactory;
import sk.upjs.strazenie.storage.DruhZvierata;
import sk.upjs.strazenie.storage.DruhZvierataDao;

@CrossOrigin
@RestController
@RequestMapping("/species")
public class DruhZvierataDaoController {

	private DruhZvierataDao druhDao = DaoFactory.INSTANCE.getDruhDao();
	
	@GetMapping
	public List<DruhZvierata> getAll() {
		return druhDao.getAll();
	}
	
}
