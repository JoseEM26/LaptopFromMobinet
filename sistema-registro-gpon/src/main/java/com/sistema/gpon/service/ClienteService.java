package com.sistema.gpon.service;

import java.util.List;

import com.sistema.gpon.dto.ClienteFilter;
import com.sistema.gpon.model.Cliente;
import com.sistema.gpon.utils.ResultadoResponse;

public interface ClienteService {
    Cliente crearClientenew(Cliente cliente);
    ResultadoResponse crearCliente(Cliente cliente);
    List<Cliente> listarClientes();
    List<Cliente> listarFiltros(ClienteFilter filtro);
    Cliente buscarPorId(String id);
    Cliente modificarClientenew(Cliente cliente);
    ResultadoResponse modificarCliente(Cliente cliente);
    ResultadoResponse cambiarEstado(String id);
}
