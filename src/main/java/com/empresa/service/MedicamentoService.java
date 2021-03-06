package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Medicamento;

public interface MedicamentoService {
	
	public Medicamento insertaActualiza(Medicamento obj);
	
	public List<Medicamento> listaMedicamento();
	
	public abstract Optional<Medicamento> buscaPorId(int idMedicamento);

	public abstract List<Medicamento> listaMedicamentoPorNom(String nombre);
	
	public abstract List<Medicamento> listaMedicamentoPorStock(int stock);
}
