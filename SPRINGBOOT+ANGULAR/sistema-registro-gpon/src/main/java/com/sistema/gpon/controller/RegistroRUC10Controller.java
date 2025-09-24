package com.sistema.gpon.controller;

import com.sistema.gpon.dto.RegistroFilter;
import com.sistema.gpon.dto.RucDTOCrear;
import com.sistema.gpon.dto.RucDTOActualizar;
import com.sistema.gpon.model.*;
import com.sistema.gpon.service.ReportService;
import com.sistema.gpon.service.impl.*;
import com.sistema.gpon.utils.Alert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/registros")
public class RegistroRUC10Controller {

    @Autowired
    private RegistroRUC10ServiceImpl _registroRUC10Service;

    @Autowired
    private ReportService reporteService;


    @Autowired
    private UsuarioServiceImpl _usuarioService;

    @Autowired
    private PromocionServiceImpl _promocionService;

    @Autowired
    private PlanServiceImpl _planService;

    @Autowired
    private SectorServiceImpl _seSectorService;

    @Autowired
    private ClienteServiceImpl _ClienteService;

    @Autowired
    private DistritoServiceImpl _disDistritoService;

    @Autowired
    private ContactoPrincipalImpl _ContactoPrincipalService;

    @Autowired
    private ContactoSecundarioImpl _ContactoSecundarioService;

    @Autowired
    private CronogramaImpl _CronogramaService;

    @Autowired
    private EstadoRegistroImpl _EstadoRegistro;

    @GetMapping({"", "/"})
    public String listado(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("lstEstados", _EstadoRegistro.listarEstado());
        model.addAttribute("filtro", new RegistroFilter());
        model.addAttribute("lstRegistros", _registroRUC10Service.listarRegistros());

        return "registros/index";
    }

    @GetMapping("/filtrado")
    public String filtrado(@ModelAttribute RegistroFilter filtro, Model model) {
        model.addAttribute("lstEstados", _EstadoRegistro.listarEstado());
        model.addAttribute("filtro", filtro);
        model.addAttribute("lstRegistros", _registroRUC10Service.listarFiltros(filtro));

        return "registros/index";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(HttpServletRequest request, Model model, @PathVariable int id) {
        RegistroRUC10 registroRUC10 = _registroRUC10Service.buscarPorId(id);
        Cronograma cronograma = _CronogramaService.buscarPorId(registroRUC10.getCronograma().getIdCronograma());
        Cliente cliente = _ClienteService.buscarPorId(registroRUC10.getCliente().getDniCliente());
        ContactoPrincipal contactoPrincipal = _ContactoPrincipalService.buscarPorId(registroRUC10.getContactoPrincipal().getIdContactoPrincipal());
        ContactoSecundario contactoSecundario = _ContactoSecundarioService.buscarPorId(registroRUC10.getContactoSecundario().getIdContactoSecundario());
        Usuario consultor = _usuarioService.buscarPorId(registroRUC10.getUsuarioConsultor().getIdUsuario());
        Usuario supervisor = _usuarioService.buscarPorId(registroRUC10.getUsuarioSupervisor().getIdUsuario());
        Plan plan = _planService.buscarPorId(registroRUC10.getPlan().getIdPlan());
        Promocion promocion = _promocionService.buscarPorId(registroRUC10.getPromocion().getIdPromocion());

        model.addAttribute("registro", registroRUC10);
        model.addAttribute("cronograma", cronograma);
        model.addAttribute("cliente", cliente);
        model.addAttribute("contactoPrincipal", contactoPrincipal);
        model.addAttribute("contactoSecundario", contactoSecundario);
        model.addAttribute("consultor", consultor);
        model.addAttribute("supervisor", supervisor);
        model.addAttribute("plan", plan);
        model.addAttribute("promocion", promocion);

        return "registros/detalle";
    }

    private void cargarDatosSelects(Model model) {
        model.addAttribute("consultores", _usuarioService.findByRol_Descripcion("Consultor"));
        model.addAttribute("supervisores", _usuarioService.findByRol_Descripcion("Supervisor"));
        model.addAttribute("promociones", _promocionService.listarPromociones());
        model.addAttribute("planes", _planService.listarTodoPlanes());
        model.addAttribute("sectores", _seSectorService.listarSectores());
        model.addAttribute("distritos", _disDistritoService.listarDistritos());
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        cargarDatosSelects(model);
        model.addAttribute("ruc10DTO", new RucDTOCrear());

        return "registros/nuevo";
    }

    @PostMapping("/registrar")
    @Transactional
    public String registrar(@Validated @ModelAttribute("ruc10DTO") RucDTOCrear ruc10DTO, BindingResult bindingResult, Model model, RedirectAttributes flash) {
        cargarDatosSelects(model);

        if (bindingResult.hasErrors()) {
            return "registros/nuevo";
        }

        try {
            ContactoPrincipal contactoPrincipal = new ContactoPrincipal();
            contactoPrincipal.setNombreContacto(ruc10DTO.getNombreContacto());
            contactoPrincipal.setDni(ruc10DTO.getDniContacto());
            contactoPrincipal.setCorreo(ruc10DTO.getCorreoContacto());
            contactoPrincipal.setTelefono(ruc10DTO.getTelefonoContacto());
            contactoPrincipal = _ContactoPrincipalService.crearContactoPrin(contactoPrincipal);

            ContactoSecundario contactoSecundario = new ContactoSecundario();
            contactoSecundario.setNombreContacto(ruc10DTO.getNombreContactoSec());
            contactoSecundario.setDni(ruc10DTO.getDniContactoSec());
            contactoSecundario.setCorreo(ruc10DTO.getCorreoContactoSec());
            contactoSecundario.setTelefono(ruc10DTO.getTelefonoContactoSec());
            contactoSecundario = _ContactoSecundarioService.creaContactoSec(contactoSecundario);

            Cronograma cronograma = new Cronograma();
            cronograma.setUbicacionInstalacion(
                    ruc10DTO.getNombreDistrito() + " " +         // Ej: JIRON PARURO
                            "NRO." + ruc10DTO.getNumero() + " " +        // Ej: NRO.1132
                            "DPTO/INT " + ruc10DTO.getInterior() + " " + // Ej: DPTO/INT 114
                            ruc10DTO.getObservacion() + " " +            // Ej: PISO 1 URB.AZCONA
                            "(" +
                            "LIMA-" + ruc10DTO.getDepartamento() + "-" + ruc10DTO.getProvincia() +
                            ")"
            );
            cronograma.setRangoInstalacion(ruc10DTO.getRangoInstalacion());
            cronograma = _CronogramaService.crearCronograma(cronograma);

            Cliente cliente = new Cliente();
            cliente.setDniCliente(ruc10DTO.getDniCliente());
            cliente.setRuc(ruc10DTO.getRucCliente());
            cliente.setTelefono(ruc10DTO.getTelefonoCliente());
            cliente.setNombre(ruc10DTO.getNombreCliente());
            cliente.setApellido(ruc10DTO.getApellidoCliente());
            cliente.setActivo(true);
            cliente = _ClienteService.crearClientenew(cliente);

            RegistroRUC10 rucDTO = new RegistroRUC10();
            rucDTO.setUsuarioConsultor(_usuarioService.buscarPorId(ruc10DTO.getIdUsuarioConsultor()));
            rucDTO.setUsuarioSupervisor(_usuarioService.buscarPorId(ruc10DTO.getIdUsuarioSupervisor()));
            rucDTO.setContactoPrincipal(contactoPrincipal);
            rucDTO.setContactoSecundario(contactoSecundario);
            rucDTO.setPlan(_planService.buscarPorId(ruc10DTO.getIdPlan()));
            rucDTO.setPromocion(_promocionService.buscarPorId(ruc10DTO.getIdPromocion()));
            rucDTO.setCronograma(cronograma);
            rucDTO.setCliente(cliente);
            rucDTO.setEstado(_EstadoRegistro.buscarPorId(1));
            rucDTO.setObservacion(ruc10DTO.getObservacion());
            _registroRUC10Service.crearRegistro(rucDTO);

            flash.addFlashAttribute("alert", Alert.sweetToast("Se ingreso correctamente el registro", "success", 5000));

        } catch (Exception e) {
            e.printStackTrace();
            flash.addFlashAttribute("alert", Alert.sweetAlert("ERROR", "Se produjo un error", "error"));
            model.addAttribute("error", "Ocurrió un error al registrar");
            return "/registros";
        }

        return "redirect:/registros";
    }

    @GetMapping("/edicion/{id}")
    public String edicion(@PathVariable int id, Model model) {
        cargarDatosSelects(model);

        RegistroRUC10 registroRUC10 = _registroRUC10Service.buscarPorId(id);
        Cronograma cronograma = _CronogramaService.buscarPorId(registroRUC10.getCronograma().getIdCronograma());
        Cliente cliente = _ClienteService.buscarPorId(registroRUC10.getCliente().getDniCliente());
        ContactoPrincipal contactoPrincipal = _ContactoPrincipalService.buscarPorId(registroRUC10.getContactoPrincipal().getIdContactoPrincipal());
        ContactoSecundario contactoSecundario = _ContactoSecundarioService.buscarPorId(registroRUC10.getContactoSecundario().getIdContactoSecundario());
        Usuario consultor = _usuarioService.buscarPorId(registroRUC10.getUsuarioConsultor().getIdUsuario());
        Usuario supervisor = _usuarioService.buscarPorId(registroRUC10.getUsuarioSupervisor().getIdUsuario());
        Plan plan = _planService.buscarPorId(registroRUC10.getPlan().getIdPlan());
        Promocion promocion = _promocionService.buscarPorId(registroRUC10.getPromocion().getIdPromocion());

        List<EstadoRegistro> estadosLista= _EstadoRegistro.listarEstado();

        RucDTOActualizar ruc10DTO = new RucDTOActualizar(
                registroRUC10.getIdRegistro(),
                contactoPrincipal.getNombreContacto(), contactoPrincipal.getDni(),
                contactoPrincipal.getCorreo(), contactoPrincipal.getTelefono(),
                contactoSecundario.getNombreContacto(), contactoSecundario.getDni(),
                contactoSecundario.getCorreo(), contactoSecundario.getTelefono(),
                cronograma.getRangoInstalacion(), cronograma.getUbicacionInstalacion(),
                cronograma.getFechaInstalacion(),
                consultor.getIdUsuario(), supervisor.getIdUsuario(),
                plan.getIdPlan(), promocion.getIdPromocion(),
                registroRUC10.getObservacion(), cliente.getDniCliente(),
                cliente.getRuc(), cliente.getNombre(), cliente.getApellido(),
                cliente.getTelefono(),registroRUC10.getEstado().getIdEstado(),registroRUC10.getIdSolicitud(),
                registroRUC10.getIdInstalacion(),registroRUC10.getIdCarrito()
        );
        ruc10DTO.setFechaInstalacion(cronograma.getFechaInstalacion());

        model.addAttribute("ruc10DTO", ruc10DTO);

        model.addAttribute("estados",estadosLista);
        return "registros/edicion";
    }

    @PostMapping("/guardar")
    @Transactional
    public String guardar(@Validated @ModelAttribute("ruc10DTO") RucDTOActualizar ruc10DTO, BindingResult bindingResult, Model model, RedirectAttributes flash) {
        cargarDatosSelects(model);

        if (bindingResult.hasErrors()) {
            return "registros/edicion";
        }

        try {
            RegistroRUC10 registroRUC10 = _registroRUC10Service.buscarPorId(ruc10DTO.getIdRegistro());

            ContactoPrincipal contactoPrincipal = new ContactoPrincipal();
            contactoPrincipal.setIdContactoPrincipal(registroRUC10.getContactoPrincipal().getIdContactoPrincipal());
            contactoPrincipal.setNombreContacto(ruc10DTO.getNombreContacto());
            contactoPrincipal.setDni(ruc10DTO.getDniContacto());
            contactoPrincipal.setCorreo(ruc10DTO.getCorreoContacto());
            contactoPrincipal.setTelefono(ruc10DTO.getTelefonoContacto());
            contactoPrincipal = _ContactoPrincipalService.crearContactoPrin(contactoPrincipal);

            ContactoSecundario contactoSecundario = new ContactoSecundario();
            contactoSecundario.setIdContactoSecundario(registroRUC10.getContactoSecundario().getIdContactoSecundario());
            contactoSecundario.setNombreContacto(ruc10DTO.getNombreContactoSec());
            contactoSecundario.setDni(ruc10DTO.getDniContactoSec());
            contactoSecundario.setCorreo(ruc10DTO.getCorreoContactoSec());
            contactoSecundario.setTelefono(ruc10DTO.getTelefonoContactoSec());
            contactoSecundario = _ContactoSecundarioService.creaContactoSec(contactoSecundario);

            Cronograma cronograma = new Cronograma();
            cronograma.setIdCronograma(registroRUC10.getCronograma().getIdCronograma());
            cronograma.setUbicacionInstalacion(ruc10DTO.getLugarInstalacion());
            cronograma.setRangoInstalacion(ruc10DTO.getRangoInstalacion());
            cronograma.setFechaInstalacion(ruc10DTO.getFechaInstalacion());
            cronograma = _CronogramaService.crearCronograma(cronograma);

            Cliente cliente = new Cliente();
            cliente.setDniCliente(registroRUC10.getCliente().getDniCliente());
            cliente.setDniCliente(ruc10DTO.getDniCliente());
            cliente.setRuc(ruc10DTO.getRucCliente());
            cliente.setTelefono(ruc10DTO.getTelefonoCliente());
            cliente.setNombre(ruc10DTO.getNombreCliente());
            cliente.setApellido(ruc10DTO.getApellidoCliente());
            cliente.setActivo(true);
            cliente = _ClienteService.crearClientenew(cliente);

            RegistroRUC10 rucDTO = new RegistroRUC10();
            rucDTO.setIdRegistro(registroRUC10.getIdRegistro());
            rucDTO.setUsuarioConsultor(_usuarioService.buscarPorId(ruc10DTO.getIdUsuarioConsultor()));
            rucDTO.setUsuarioSupervisor(_usuarioService.buscarPorId(ruc10DTO.getIdUsuarioSupervisor()));
            rucDTO.setContactoPrincipal(contactoPrincipal);
            rucDTO.setContactoSecundario(contactoSecundario);
            rucDTO.setPlan(_planService.buscarPorId(ruc10DTO.getIdPlan()));
            rucDTO.setPromocion(_promocionService.buscarPorId(ruc10DTO.getIdPromocion()));
            rucDTO.setCronograma(cronograma);
            rucDTO.setCliente(cliente);
            rucDTO.setEstado(_EstadoRegistro.buscarPorId(1));
            rucDTO.setObservacion(ruc10DTO.getObservacion());
            rucDTO.setIdCarrito(ruc10DTO.getIdCarrito());
            rucDTO.setIdRegistro(ruc10DTO.getIdRegistro());
            rucDTO.setIdSolicitud(registroRUC10.getIdSolicitud());
            rucDTO.setIdInstalacion(ruc10DTO.getIdInstalacion());
            rucDTO.setEstado(_EstadoRegistro.buscarPorId(ruc10DTO.getIdEstado()));
            _registroRUC10Service.crearRegistro(rucDTO);

            flash.addFlashAttribute("alert", Alert.sweetToast("Se actualizó correctamente la venta", "success", 5000));

        } catch (Exception e) {
            e.printStackTrace();
            flash.addFlashAttribute("alert", Alert.sweetAlert("ERROR", "Se produjo un error", "error"));
            model.addAttribute("error", "Ocurrió un error al registrar");
            return "/registros";

        }

        return "redirect:/registros";
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<byte[]> generarReporte(@PathVariable Integer id) {
        try {
            System.out.println("id: " + id);
            byte[] reporte = reporteService.generarReportParametrs("ReportRegister", id);

            if (reporte == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            String nombreArchivo = (id != null && id > 0)
                    ? "registro_ruc10_" + id + ".pdf"
                    : "registro_ruc10_todos.pdf";
            headers.add("Content-Disposition", "inline; filename=" + nombreArchivo);

            return new ResponseEntity<>(reporte, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}



