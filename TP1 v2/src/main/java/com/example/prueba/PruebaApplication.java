package com.example.prueba;

import com.example.prueba.entidades.Cliente;
import com.example.prueba.entidades.Domicilio;
import com.example.prueba.entidades.Pedido;
import com.example.prueba.repositorios.ClienteRepository;
import com.example.prueba.repositorios.DomicilioRepository;
import com.example.prueba.repositorios.FacturaRepository;
import com.example.prueba.repositorios.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.example.prueba.entidades.Factura;

import java.util.Date;

@SpringBootApplication
public class PruebaApplication {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	DomicilioRepository domicilioRepository;

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	FacturaRepository facturaRepository;

	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
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
			clienteRepository.save(cliente);

//			// Crear una factura (opcional)
//			Factura factura = new Factura();
//			factura.setNumero(1);
//			factura.setFecha(new Date());
//			factura.setTotal(100);
//			factura.setFormaPago(true);

			// Asociar la factura al pedido (si es necesario)

//			facturaRepository.save(factura);

			// Crear un Pedido con o sin Factura
			Pedido pedido = new Pedido();
			pedido.setEstado(true);
			pedido.setFecha(new Date());
			pedido.setTipoEnvio(true);
			pedido.setTotal(100);
			pedido.setIniciado();
			pedido.retiro(true);
			pedido.setFactura(null);

			// Guardar el pedido en la base de datos
			pedidoRepository.save(pedido);

			cliente.agregarPedidos(pedido);

		};
	}
}
