package com.example.prueba.repositorios;

import com.example.prueba.entidades.Factura;
import com.example.prueba.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Factura, Long> {
}
