package com.sistema.gpon.service.impl;

import java.util.List;
import java.util.Optional;

import com.sistema.gpon.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.gpon.model.Plan;
import com.sistema.gpon.repository.PlanRepository;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepository planRepository;

	@Override
	public Plan crearPlan(Plan plan) {
		return planRepository.save(plan);
	}

	@Override
	public List<Plan> listarTodoPlanes() {
		return planRepository.findAll();
	}

	@Override
	public List<Plan> listarPlanes() {
		// Si necesitas un filtrado específico, modifícalo según el método personalizado en el repositorio
		return planRepository.findAll();
	}

	@Override
	public Plan buscarPorId(Integer idPlan) {
		Optional<Plan> optional = planRepository.findById(idPlan);
		return optional.orElse(null); // O lanza una excepción si prefieres
	}

	@Override
	public Plan actualizarPlan(Plan plan) {
		if (plan.getIdPlan() == null || !planRepository.existsById(plan.getIdPlan())) {
			return null; // O lanza una excepción personalizada
		}
		return planRepository.save(plan);
	}

	@Override
	public boolean eliminarPlan(Integer idPlan) {
		if (planRepository.existsById(idPlan)) {
			planRepository.deleteById(idPlan);
			return true;
		}
		return false;
	}
}
