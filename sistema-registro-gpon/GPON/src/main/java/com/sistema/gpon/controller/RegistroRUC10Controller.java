package com.sistema.gpon.controller;

import com.sistema.gpon.model.RucDTO;
import com.sistema.gpon.model.*;
import com.sistema.gpon.service.impl.*;
import com.sistema.gpon.utils.Alert;
import com.sistema.gpon.utils.ResultadoResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/registros")
public class RegistroRUC10Controller {

    @Autowired
    private RegistroRUC10ServiceImpl _registroRUC10Service;

    @Autowired
    private UsuarioServiceImpl _usuarioService;

    @Autowired
    private PromocionServiceImpl _promocionService;

    @Autowired
    private PlanServiceImpl _planService;

    @Autowired
    private SectorServiceImpl _seSectorService;

    @Autowired
    private  ClienteServiceImpl _ClienteService;

    @Autowired
    private DistritoServiceImpl _disDistritoService;

    @Autowired
    private ContactoPrincipalImpl _ContactoPrincipalService;

    @Autowired
    private ContactoSecundarioImpl _ContactoSecundarioService;

    @Autowired
    private CronogramaImpl _CronogramaService;


    @Autowired
    private  EstadoRegistroImpl _EstadoRegistro;

    @GetMapping({"", "/"})
    public String index(HttpServletRequest request,Model model){
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("listaruc",_registroRUC10Service.listarRegistrosActivos(1));
//        model.addAttribute("listaruc",_registroRUC10Service.listarRegistros());
        return "registros/index";
    }

    @PostMapping("/filtrar")
    public String filtrar(HttpServletRequest request, Model model, @RequestParam("estado") int estado) {
        model.addAttribute("uri", request.getRequestURI());
        model.addAttribute("listaruc", _registroRUC10Service.listarRegistrosActivos(estado));
        return "registros/index";
    }


    @GetMapping("/nuevo")
    public String nuevo( Model model) {
        model.addAttribute("Consultor", _usuarioService.findByRol_Descripcion("Consultor"));
        model.addAttribute("Supervisor", _usuarioService.findByRol_Descripcion("Supervisor"));
        model.addAttribute("promocion", _promocionService.listarPromociones());
        model.addAttribute("plam", _planService.listarTodoPlanes());
        model.addAttribute("sector" , _seSectorService.listarSectores() );
        model.addAttribute("distrito" , _disDistritoService.listarDistritos());
        model.addAttribute("ruc10DTO" , new RucDTO());

        return "registros/nuevo";
    }

    @PostMapping("/create")
    @Transactional
    public String create(HttpServletRequest request, Model model , @ModelAttribute RucDTO ruc10DTO , RedirectAttributes flash) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("Consultor", _usuarioService.findByRol_Descripcion("Consultor"));
        model.addAttribute("Supervisor", _usuarioService.findByRol_Descripcion("Supervisor"));
        model.addAttribute("promocion", _promocionService.listarPromociones());
        model.addAttribute("plam", _planService.listarTodoPlanes());
        model.addAttribute("sector" , _seSectorService.listarSectores() );
        model.addAttribute("distrito" , _disDistritoService.listarDistritos());

        try {
            System.out.println("===== DATOS RECIBIDOS EN EL FORMULARIO =====");
            System.out.println("Nombre Contacto Principal: " + ruc10DTO.getNombreContacto());
            System.out.println("DNI Contacto Principal: " + ruc10DTO.getDniContacto());
            System.out.println("Correo Contacto Principal: " + ruc10DTO.getCorreoContacto());
            System.out.println("Teléfono Contacto Principal: " + ruc10DTO.getTelefonoContacto());

            System.out.println("Nombre Contacto Secundario: " + ruc10DTO.getNombreContactoSec());
            System.out.println("DNI Contacto Secundario: " + ruc10DTO.getDniContactoSec());
            System.out.println("Correo Contacto Secundario: " + ruc10DTO.getCorreoContactoSec());
            System.out.println("Teléfono Contacto Secundario: " + ruc10DTO.getTelefonoContactoSec());

            System.out.println("Ubicación Distrito: " + ruc10DTO.getNombreDistrito());
            System.out.println("Ubicación Sector: " + ruc10DTO.getNombreSector());
            System.out.println("Referencia: " + ruc10DTO.getReferencia());
            System.out.println("Rango de Instalación: " + ruc10DTO.getRangoInstalacion());

            System.out.println("DNI Cliente: " + ruc10DTO.getDniCliente());
            System.out.println("RUC Cliente: " + ruc10DTO.getRucCliente());
            System.out.println("Teléfono Cliente: " + ruc10DTO.getTelefonoCliente());
            System.out.println("Nombre Cliente: " + ruc10DTO.getNombreCliente());
            System.out.println("Apellido Cliente: " + ruc10DTO.getApellidoCliente());

            System.out.println("ID Usuario Consultor: " + ruc10DTO.getIdUsuarioConsulto());
            System.out.println("ID Usuario Supervisor: " + ruc10DTO.getIdUsuarioSupervisor());
            System.out.println("ID Plan: " + ruc10DTO.getIdPlan());
            System.out.println("ID Promoción: " + ruc10DTO.getIdPromocion());
            System.out.println("Observación: " + ruc10DTO.getObservacion());
            System.out.println("=============================================");

            ContactoPrincipal contactoPrincipal=new ContactoPrincipal();
            contactoPrincipal.setNombreContacto(ruc10DTO.getNombreContacto());
            contactoPrincipal.setDni(ruc10DTO.getDniContacto());
            contactoPrincipal.setCorreo(ruc10DTO.getCorreoContacto());
            contactoPrincipal.setTelefono(ruc10DTO.getTelefonoContacto());
            contactoPrincipal = _ContactoPrincipalService.crearContactoPrin(contactoPrincipal);

            ContactoSecundario contactoSecundario=new ContactoSecundario();
            contactoSecundario.setNombreContacto(ruc10DTO.getNombreContactoSec());
            contactoSecundario.setDni(ruc10DTO.getDniContactoSec());
            contactoSecundario.setCorreo(ruc10DTO.getCorreoContactoSec());
            contactoSecundario.setTelefono(ruc10DTO.getTelefonoContactoSec());
            contactoSecundario = _ContactoSecundarioService.creaContactoSec(contactoSecundario);

            Cronograma cronograma= new Cronograma();
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

            Cliente cliente= new Cliente();
            cliente.setDniCliente(ruc10DTO.getDniCliente());
            cliente.setRuc(ruc10DTO.getRucCliente());
            cliente.setTelefono(ruc10DTO.getTelefonoCliente());
            cliente.setNombre(ruc10DTO.getNombreCliente());
            cliente.setApellido(ruc10DTO.getApellidoCliente());
            cliente = _ClienteService.crearClientenew(cliente);

            RegistroRUC10 rucDTO= new RegistroRUC10();
            rucDTO.setUsuarioConsulto(_usuarioService.buscarPorId(ruc10DTO.getIdUsuarioConsulto()));
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

//            flash.addFlashAttribute("alert", Alert.sweetAlertSuccess("Se ingreso correctamente el registro"));
            flash.addFlashAttribute("alert", Alert.sweetToast("Se creo correctamente la venta", "success", 5000));

        }catch (Exception e){
            e.printStackTrace();
            flash.addFlashAttribute("alert", Alert.sweetAlert("ERROR" ,"Se produjo un error" ,"error"));
            model.addAttribute("error", "Ocurrió un error al registrar");
            return "/registros";

        }

        return "redirect:/registros";
    }

    @GetMapping("/actualizar/{id}")
    public String Actualizar(@PathVariable int id, Model model) {
        model.addAttribute("Consultor", _usuarioService.findByRol_Descripcion("Consultor"));
        model.addAttribute("Supervisor", _usuarioService.findByRol_Descripcion("Supervisor"));
        model.addAttribute("promocion", _promocionService.listarPromociones());
        model.addAttribute("plam", _planService.listarTodoPlanes());
        model.addAttribute("sector", _seSectorService.listarSectores());
        model.addAttribute("distrito", _disDistritoService.listarDistritos());

        RegistroRUC10 registroRUC10 = _registroRUC10Service.buscarPorId(id);
        Cronograma cronograma = _CronogramaService.buscarPorId(registroRUC10.getCronograma().getIdCronograma());
        Cliente cliente = _ClienteService.buscarPorDni(registroRUC10.getCliente().getDniCliente());
        ContactoPrincipal contactoPrincipal = _ContactoPrincipalService.buscarPorId(registroRUC10.getContactoPrincipal().getIdContactoPrincipal());
        ContactoSecundario contactoSecundario = _ContactoSecundarioService.buscarPorId(registroRUC10.getContactoSecundario().getIdContactoSecundario());
        Usuario consultor = _usuarioService.buscarPorId(registroRUC10.getUsuarioConsulto().getIdUsuario());
        Usuario supervisor = _usuarioService.buscarPorId(registroRUC10.getUsuarioSupervisor().getIdUsuario());
        Plan plan = _planService.buscarPorId(registroRUC10.getPlan().getIdPlan());
        Promocion promocion = _promocionService.buscarPorId(registroRUC10.getPromocion().getIdPromocion());



        RucDTOUpdate ruc10DTO = new RucDTOUpdate(
                registroRUC10.getIdRegistro(),
                contactoPrincipal.getNombreContacto(), contactoPrincipal.getDni(),
                contactoPrincipal.getCorreo(), contactoPrincipal.getTelefono(),
                contactoSecundario.getNombreContacto(), contactoSecundario.getDni(),
                contactoSecundario.getCorreo(), contactoSecundario.getTelefono(),
                cronograma.getRangoInstalacion(), cronograma.getUbicacionInstalacion(),
                cronograma.getFechaInstalacion(), // ✅ sin formatear
                consultor.getIdUsuario(), supervisor.getIdUsuario(),
                plan.getIdPlan(), promocion.getIdPromocion(),
                registroRUC10.getObservacion(), cliente.getDniCliente(),
                cliente.getRuc(), cliente.getNombre(), cliente.getApellido(),
                cliente.getTelefono()
        );
        ruc10DTO.setFechaInstalacion(cronograma.getFechaInstalacion());
        // 🔍 Verifica si llega el valor correctamente
        System.out.println("📅 Fecha de instalación: " + cronograma.getFechaInstalacion());

        model.addAttribute("ruc10DTOActualizar", ruc10DTO);

        return "registros/actualizar";
    }




    @PostMapping("/save")
    @Transactional
    public String save(HttpServletRequest request, Model model , @ModelAttribute RucDTOUpdate ruc10DTO , RedirectAttributes flash) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("Consultor", _usuarioService.findByRol_Descripcion("Consultor"));
        model.addAttribute("Supervisor", _usuarioService.findByRol_Descripcion("Supervisor"));
        model.addAttribute("promocion", _promocionService.listarPromociones());
        model.addAttribute("plam", _planService.listarTodoPlanes());
        model.addAttribute("sector" , _seSectorService.listarSectores() );
        model.addAttribute("distrito" , _disDistritoService.listarDistritos());

        try {
            System.out.println("===== DATOS RECIBIDOS EN EL FORMULARIO =====");
            System.out.println("Nombre Contacto Principal: " + ruc10DTO.getNombreContacto());
            System.out.println("DNI Contacto Principal: " + ruc10DTO.getDniContacto());
            System.out.println("Correo Contacto Principal: " + ruc10DTO.getCorreoContacto());
            System.out.println("Teléfono Contacto Principal: " + ruc10DTO.getTelefonoContacto());

            System.out.println("Nombre Contacto Secundario: " + ruc10DTO.getNombreContactoSec());
            System.out.println("DNI Contacto Secundario: " + ruc10DTO.getDniContactoSec());
            System.out.println("Correo Contacto Secundario: " + ruc10DTO.getCorreoContactoSec());
            System.out.println("Teléfono Contacto Secundario: " + ruc10DTO.getTelefonoContactoSec());

//            System.out.println("Ubicación Distrito: " + ruc10DTO.getNombreDistrito());
//            System.out.println("Ubicación Sector: " + ruc10DTO.getNombreSector());
//            System.out.println("Referencia: " + ruc10DTO.getReferencia());
            System.out.println("Referencia: " + ruc10DTO.getLugarInstalacion());
            System.out.println("Rango de Instalación: " + ruc10DTO.getRangoInstalacion());

            System.out.println("DNI Cliente: " + ruc10DTO.getDniCliente());
            System.out.println("RUC Cliente: " + ruc10DTO.getRucCliente());
            System.out.println("Teléfono Cliente: " + ruc10DTO.getTelefonoCliente());
            System.out.println("Nombre Cliente: " + ruc10DTO.getNombreCliente());
            System.out.println("Apellido Cliente: " + ruc10DTO.getApellidoCliente());

            System.out.println("ID Usuario Consultor: " + ruc10DTO.getIdUsuarioConsulto());
            System.out.println("ID Usuario Supervisor: " + ruc10DTO.getIdUsuarioSupervisor());
            System.out.println("ID Plan: " + ruc10DTO.getIdPlan());
            System.out.println("ID Promoción: " + ruc10DTO.getIdPromocion());
            System.out.println("Observación: " + ruc10DTO.getObservacion());
            System.out.println("=============================================");


            RegistroRUC10 registroRUC10=_registroRUC10Service.buscarPorId(ruc10DTO.getIdRegistro());

            ContactoPrincipal contactoPrincipal=new ContactoPrincipal();
            contactoPrincipal.setIdContactoPrincipal(registroRUC10.getContactoPrincipal().getIdContactoPrincipal());
            contactoPrincipal.setNombreContacto(ruc10DTO.getNombreContacto());
            contactoPrincipal.setDni(ruc10DTO.getDniContacto());
            contactoPrincipal.setCorreo(ruc10DTO.getCorreoContacto());
            contactoPrincipal.setTelefono(ruc10DTO.getTelefonoContacto());
            contactoPrincipal = _ContactoPrincipalService.crearContactoPrin(contactoPrincipal);

            ContactoSecundario contactoSecundario=new ContactoSecundario();
            contactoSecundario.setIdContactoSecundario(registroRUC10.getContactoSecundario().getIdContactoSecundario());
            contactoSecundario.setNombreContacto(ruc10DTO.getNombreContactoSec());
            contactoSecundario.setDni(ruc10DTO.getDniContactoSec());
            contactoSecundario.setCorreo(ruc10DTO.getCorreoContactoSec());
            contactoSecundario.setTelefono(ruc10DTO.getTelefonoContactoSec());
            contactoSecundario = _ContactoSecundarioService.creaContactoSec(contactoSecundario);

            Cronograma cronograma= new Cronograma();
            cronograma.setIdCronograma(registroRUC10.getCronograma().getIdCronograma());
            cronograma.setUbicacionInstalacion(ruc10DTO.getLugarInstalacion());
            cronograma.setRangoInstalacion(ruc10DTO.getRangoInstalacion());
            cronograma.setFechaInstalacion(ruc10DTO.getFechaInstalacion());
            cronograma = _CronogramaService.crearCronograma(cronograma);

            Cliente cliente= new Cliente();
            cliente.setDniCliente(registroRUC10.getCliente().getDniCliente());
            cliente.setDniCliente(ruc10DTO.getDniCliente());
            cliente.setRuc(ruc10DTO.getRucCliente());
            cliente.setTelefono(ruc10DTO.getTelefonoCliente());
            cliente.setNombre(ruc10DTO.getNombreCliente());
            cliente.setApellido(ruc10DTO.getApellidoCliente());
            cliente = _ClienteService.crearClientenew(cliente);

            RegistroRUC10 rucDTO= new RegistroRUC10();
            rucDTO.setIdRegistro(registroRUC10.getIdRegistro());
            rucDTO.setUsuarioConsulto(_usuarioService.buscarPorId(ruc10DTO.getIdUsuarioConsulto()));
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

//            flash.addFlashAttribute("alert", Alert.sweetAlertSuccess("Se ingreso correctamente el registro"));
            flash.addFlashAttribute("alert", Alert.sweetToast("Se Actualizo correctamente la venta", "success", 5000));

        }catch (Exception e){
            e.printStackTrace();
            flash.addFlashAttribute("alert", Alert.sweetAlert("ERROR" ,"Se produjo un error" ,"error"));
            model.addAttribute("error", "Ocurrió un error al registrar");
            return "/registros";

        }

        return "redirect:/registros";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id , RedirectAttributes flash){

        ResultadoResponse response= _registroRUC10Service.cambiarEstado(id);
        flash.addFlashAttribute("alert", Alert.sweetToast("Registro eliminada correctamente", "success", 3000));



        return "redirect:/registros";
    }



}