package com.sistema.gpon.controller;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.gpon.service.PlanService;
import com.sistema.gpon.service.impl.ClienteServiceImpl;
import com.sistema.gpon.utils.Alert;
import com.sistema.gpon.utils.ResultadoResponse;
import com.sistema.gpon.model.*;

@Controller
@RequestMapping("/planes")
public class PlanController {

    private final ClienteServiceImpl clienteServiceImpl;

    @Autowired
    private PlanService planService;

    PlanController(ClienteServiceImpl clienteServiceImpl) {
        this.clienteServiceImpl = clienteServiceImpl;
    }

    @GetMapping({"", "/"})
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());
        List<Plan> listaPlan = planService.listarTodoPlanes();
        model.addAttribute("lstPlan", listaPlan);
        return "planes/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("plan", new Plan());
        return "planes/nuevo";
    }

    @PostMapping("/registrar")
    public String registrarPlan(@Validated @ModelAttribute Plan plan, BindingResult bindingResults, Model model, RedirectAttributes flash) {

        ResultadoResponse result = planService.crearPlan(plan);

        if (!result.success) {
            model.addAttribute("alert", Alert.sweetAlertError("Error " + result.mensaje));
            return ("planes/nuevo");
        } else {
            flash.addFlashAttribute("alert", Alert.sweetToast(result.mensaje, "success", 5000));
        }
        return "redirect:/planes";
    }

    @GetMapping("/editarPlan/{id}")
    public String editarPlan(@PathVariable Integer id, Model model) {
        Plan planObtenido = planService.buscarPorId(id);
        model.addAttribute("plan", planObtenido);
        return "planes/edicion";
    }

    @PostMapping("/guardarPlan")
    public String editarPlanSave(@Validated @ModelAttribute Plan plan, BindingResult bindingResult, Model model,
                                 RedirectAttributes flash) {
        ResultadoResponse response = planService.actualizarPlan(plan);

        if (!response.success) {
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "planes/edicion";
        } else {
            model.addAttribute("alert", Alert.sweetToast(response.mensaje, "Succes", 5000));
            return "redirect:/planes";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes flash) {
        boolean eliminado = planService.eliminarPlan(id);
        if (eliminado) {
            flash.addFlashAttribute("alert", Alert.sweetToast("Plan eliminada correctamente", "success", 3000));
        } else {
            flash.addFlashAttribute("alert", Alert.sweetAlertError("No se pudo eliminar el plan."));
        }
        return "redirect:/planes";
    }
}
