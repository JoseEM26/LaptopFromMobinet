package com.sistema.gpon.service;

import java.util.List;

import com.sistema.gpon.model.Plan;
import com.sistema.gpon.utils.ResultadoResponse;

public interface PlanService {
    ResultadoResponse crearPlan(Plan plan);
    List<Plan> listarPlanes();
    List<Plan> listarTodoPlanes();
    Plan buscarPorId(Integer idPlan);
    ResultadoResponse actualizarPlan(Plan plan);
    boolean eliminarPlan(Integer idPlan);
}
