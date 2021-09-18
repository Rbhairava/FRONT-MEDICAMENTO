package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.empresa.entity.Medicamento;
import com.empresa.service.MedicamentoService;
import com.empresa.util.Constantes;

@RestController
@RequestMapping("/rest/medicamento")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicamentoController {
	
	@Autowired
	private MedicamentoService service;
	
	// Lista
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Medicamento>> listaMedicamentos(){
		List<Medicamento> lista = service.listaMedicamento();
		return ResponseEntity.ok(lista);
		
	}
	
	
	

	//Inserta
	@PostMapping
	@ResponseBody
public  ResponseEntity<Map<String, Object>> registraAlumno(@RequestBody Medicamento obj){
		
		Map<String, Object> salida = new HashMap<>();
		try {
			
			Medicamento objSalida = service.insertaActualiza(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			}else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
	
	@PutMapping
	@ResponseBody

	public ResponseEntity<Medicamento> actualizaAlumno(@RequestBody Medicamento obj){

		if (obj == null) {

			return ResponseEntity.badRequest().build();

		}else {

			Optional<Medicamento> optMedicamento = service.buscaPorId(obj.getIdMedicamento());

			if (optMedicamento.isPresent()) {

				Medicamento objActualizado = service.insertaActualiza(obj);

				return ResponseEntity.ok(objActualizado);

			}else {

				return ResponseEntity.badRequest().build();

			}

		}

	}
	
	@GetMapping("/id/{paramId}")
	@ResponseBody

	public ResponseEntity<Medicamento> listaMedicamentoPorId(@PathVariable("paramId")int idMedicamento){
		Optional<Medicamento> optMedicamento = service.buscaPorId(idMedicamento);
		if (optMedicamento.isPresent()) {
			return ResponseEntity.ok(optMedicamento.get());
		}else {
			return ResponseEntity.badRequest().build();
		}
	}


	@GetMapping("/nombre/{paramNom}")
	@ResponseBody
	public ResponseEntity<List<Medicamento>> listaMedicamentoPorNom(@PathVariable("paramNom")String nombre){
		List<Medicamento> lista =service.listaMedicamentoPorNom(nombre);
		if (CollectionUtils.isEmpty(lista)) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(lista);		
		}
	}
	
	
	@GetMapping("/stock/{paramStock}")
	@ResponseBody
	public ResponseEntity<List<Medicamento>> listaMedicamentoPorStock(@PathVariable("paramStock")int stock){
		List<Medicamento> lista =service.listaMedicamentoPorStock(stock);
		if (CollectionUtils.isEmpty(lista)) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(lista);		
		}
	}
}