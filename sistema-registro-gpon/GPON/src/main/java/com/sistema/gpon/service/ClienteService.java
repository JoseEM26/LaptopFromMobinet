package com.sistema.gpon.service;

import java.util.List;

import com.sistema.gpon.model.Cliente;
import com.sistema.gpon.utils.ResultadoResponse;

public interface ClienteService {
    Cliente crearClientenew(Cliente cliente);
    ResultadoResponse crearCliente(Cliente cliente);
    List<Cliente> listarClientes();
    Cliente buscarPorDni(String dni);
    Cliente modificarClientenew(Cliente cliente);
    ResultadoResponse modificarCliente(Cliente cliente);
    boolean eliminarCliente(String dni);
}
