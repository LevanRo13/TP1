package com.example.prueba;

import com.example.prueba.entidades.Cliente;
import com.example.prueba.entidades.Domicilio;
import com.example.prueba.entidades.Pedido;
import com.example.prueba.repositorios.ClienteRepository;
import com.example.prueba.repositorios.DomicilioRepository;
import com.example.prueba.repositorios.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;

@SpringBootApplication
public class PruebaApplication {

//	@Autowired
//	ClienteRepository clienteRepository;

	@Autowired
	DomicilioRepository domicilioRepository;

	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ClienteRepository clienteRepo, ClienteService clienteService) {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO---------");

			// Crear instancias de Domicilio
			Domicilio domicilio1 = new Domicilio();
			domicilio1.setCalle("Calle 1");
			domicilio1.setNumero("123");

			Domicilio domicilio2 = new Domicilio();
			domicilio2.setCalle("Calle 2");
			domicilio2.setNumero("456");

			// Crear instancia de Cliente
			Cliente cliente = new Cliente();
			cliente.setNombre("Juan");
			cliente.setApellido("Pérez");
			cliente.setTelefono("4850202");
			cliente.setEmail("Juan_123@gmail.com");

			// Agregar domicilios al cliente
			cliente.agregarDomicilio(domicilio1);
			cliente.agregarDomicilio(domicilio2);

			// Guardar el cliente en la base de datos
			clienteService.guardarCliente(cliente);

			Pedido pedido = new Pedido();
			pedido.setEstado(true);
			pedido.setFecha(new Date());
			pedido.setTipoEnvio(true);
			pedido.setTotal(100.0);
			pedido.setIniciado();
			pedido.retiro(true);

			pedido.mostrarEstado();

			// Recuperar el objeto Cliente desde la base de datos por ID
			Cliente clienteRecuperado = clienteService.buscarClientePorId(cliente.getId());

			//Buscar y mostrar Cliente
			if (clienteRecuperado != null) {
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				System.out.println("Email: " + clienteRecuperado.getEmail());
				System.out.println("----------------------------------------");
				clienteRecuperado.mostrarDomicilio();
			}

			if (pedidoRecuperado != null) {
				System.out.println("Estado: " + pedidoRecuperado.isEstado());
				System.out.println("Fecha: " + pedidoRecuperado.getFecha());
				System.out.println("Tipo de Envío: " + pedidoRecuperado.isTipoEnvio());
				System.out.println("Total: " + pedidoRecuperado.getTotal());
				System.out.println("Estado Actual: " + pedidoRecuperado.getEstadoActual());
				System.out.println("Envío: " + pedidoRecuperado.getEnvio());
			}

		};
	}
}

@Service
class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente guardarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente buscarClientePorId(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}
}
