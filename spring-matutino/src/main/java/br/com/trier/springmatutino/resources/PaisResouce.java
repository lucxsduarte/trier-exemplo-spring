package br.com.trier.springmatutino.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trier.springmatutino.domain.Pais;
import br.com.trier.springmatutino.domain.dto.PaisDTO;
import br.com.trier.springmatutino.services.PaisService;

@RestController
@RequestMapping (value = "/pais")
public class PaisResouce {

	@Autowired
	private PaisService service;
	
	//@Secured({"ROLE_ADMIN"})
	@PostMapping
	public ResponseEntity<PaisDTO> insert(@RequestBody PaisDTO pais) {
		Pais newPais = service.salvar(new Pais(pais));
		return ResponseEntity.ok(newPais.toDto());
	}
	
	//@Secured({"ROLE_USER"})
	@GetMapping ("/{id}")
	public ResponseEntity<PaisDTO> buscaPorCodigo(@PathVariable Integer id) {
		Pais pais = service.findById(id);
		return ResponseEntity.ok(pais.toDto());
	}
	
	//@Secured({"ROLE_USER"})
	@GetMapping
	public ResponseEntity<List<PaisDTO>> listarTodos() {
		return ResponseEntity.ok(service.listAll().stream().map(pais -> pais.toDto()).toList());
	}
	
	//@Secured({"ROLE_ADMIN"})
	@PutMapping ("/{id}")
	public ResponseEntity<PaisDTO> update(@PathVariable Integer id, @RequestBody PaisDTO paisDTO){
		Pais pais = new Pais(paisDTO);
		pais.setId(id);
		pais = service.update(pais);
		return ResponseEntity.ok(pais.toDto());
	}
	
	//@Secured({"ROLE_ADMIN"})
	@DeleteMapping ("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	//@Secured({"ROLE_USER"})
	@GetMapping ("/name/{name}")
	public ResponseEntity<List<PaisDTO>> buscaPorNome(@PathVariable String name) {
		return ResponseEntity.ok(service.findByNameIgnoreCase(name).stream().map(pais -> pais.toDto()).toList());
		
	}
	
	//@Secured({"ROLE_USER"})
	@GetMapping ("/name/contem/{name}")
	public ResponseEntity<List<PaisDTO>> buscaPorNomeContem(@PathVariable String name) {
		return ResponseEntity.ok(service.findByNameContains(name).stream().map(pais -> pais.toDto()).toList());
	}
	
}
