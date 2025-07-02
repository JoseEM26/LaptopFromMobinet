package com.sistema.gpon.service;

import java.util.List;

import com.sistema.gpon.model.Plan;

public interface PlanService {
    Plan crearPlan(Plan plan);
    List<Plan> listarPlanes();
    List<Plan> listarTodoPlanes();
    Plan buscarPorId(Integer idPlan);
    Plan actualizarPlan(Plan plan);
    boolean eliminarPlan(Integer idPlan);
}
