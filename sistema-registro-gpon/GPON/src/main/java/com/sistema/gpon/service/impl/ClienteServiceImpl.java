package com.sistema.gpon.service.impl;

import java.util.List;

import com.sistema.gpon.model.Promocion;
import com.sistema.gpon.service.ClienteService;
import com.sistema.gpon.utils.ResultadoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.gpon.model.Cliente;
import com.sistema.gpon.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente crearClientenew(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public ResultadoResponse crearCliente(Cliente cliente) {
        try {
            Cliente registrado = clienteRepository.save(cliente);

            String mensaje = String.format("Cliente con DNI %s creado", registrado.getDniCliente());
            return new ResultadoResponse(true, mensaje);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultadoResponse(false, "Error al crear: " + ex.getMessage());
        }
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorDni(String dni) {
        return clienteRepository.findById(dni).orElseThrow(null);
    }

    @Override
    public Cliente modificarClientenew(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public ResultadoResponse modificarCliente(Cliente cliente) {
        try {
            Cliente actualizado = clienteRepository.save(cliente);

            String mensaje = String.format("Cliente con DNI %s actualizado", actualizado.getDniCliente());
            return new ResultadoResponse(true, mensaje);

        } catch (Exception ex) {
            return new ResultadoResponse(false, "Error al actualizar: " + ex.getMessage());
        }
    }

    @Override
    public boolean eliminarCliente(String dni) {
        try {
            if (clienteRepository.existsById(dni)) {
                clienteRepository.deleteById(dni);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
