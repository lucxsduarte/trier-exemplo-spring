package br.com.trier.springmatutino.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trier.springmatutino.domain.Campeonato;
import br.com.trier.springmatutino.services.CampeonatoService;

@RestController
@RequestMapping (value = "/camp")
public class CampeonatoResource {
	
	@Autowired
	private CampeonatoService service;
	
	@PostMapping
	public ResponseEntity<Campeonato> insert(@RequestBody Campeonato campeonato) {
		Campeonato newCampeonato = service.salvar(campeonato);
		return newCampeonato != null ? ResponseEntity.ok(newCampeonato) : ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Campeonato> buscaPorCodigo(@PathVariable Integer id) {
		Campeonato campeonato = service.findById(id);
		return campeonato != null ? ResponseEntity.ok(campeonato) : ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Campeonato>> listarTodos() {
		List<Campeonato> lista = service.listAll();
		return lista.size() > 0 ? ResponseEntity.ok(lista) : ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Campeonato> update(@PathVariable Integer id, @RequestBody Campeonato campeonato) {
		campeonato.setId(id);
		campeonato = service.update(campeonato);
		return campeonato != null ? ResponseEntity.ok(campeonato) : ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/ano/{ano}")
	public ResponseEntity<List<Campeonato>> buscarPorAno(@PathVariable Integer ano) {
		List<Campeonato> lista = service.findByAno(ano);
		return lista.size() > 0 ? ResponseEntity.ok(lista) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/ano-entre/{ano1}/{ano2}")
	public ResponseEntity<List<Campeonato>> buscaPorAnoEntre(@PathVariable Integer ano1, @PathVariable Integer ano2) {
		List<Campeonato> lista = service.findByAnoBetween(ano1, ano2);
		return lista.size() > 0 ? ResponseEntity.ok(lista) : ResponseEntity.noContent().build();
	}
	
	@GetMapping ("/desc/{description}")
	public ResponseEntity<List<Campeonato>> buscaPorDescriptionIgnoreCase(@PathVariable String description) {
		List<Campeonato> lista = service.findByDescriptionIgnoreCase(description);
		return lista.size() > 0 ? ResponseEntity.ok(lista) : ResponseEntity.noContent().build();
	}
	
	@GetMapping ("/desc-contem/{description}")
	public ResponseEntity<List<Campeonato>> buscaPorDescriptionComtem(@PathVariable String description) {
		List<Campeonato> lista = service.findByDescriptionContainsIgnoreCase(description);
		return lista.size() > 0 ? ResponseEntity.ok(lista) : ResponseEntity.noContent().build();
	}
	
	@GetMapping ("/ano-desc/{ano}/{description}")
	public ResponseEntity<List<Campeonato>> buscaPorDescription(@PathVariable Integer ano, @PathVariable String description) {
		List<Campeonato> lista = service.findByAnoAndDescription(ano, description);
		return lista.size() > 0 ? ResponseEntity.ok(lista) : ResponseEntity.noContent().build();
	}


}
