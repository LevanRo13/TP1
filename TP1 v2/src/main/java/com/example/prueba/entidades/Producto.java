package com.example.prueba.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto extends BaseEntidad{

    private boolean tipo =true;
    private int tiempoEstimadoCocina;
    private String denominacion;
    private double precioVenta;
    private double precioCompra;
    private int stockActual;
    private int stockMinimo;
    private String unidadMedida;
    private String receta;
    private String TipoProducto;

    @ManyToOne()
    @JoinColumn(name = "Pkproducto")
    Rubro rubro;

    public void retiro(boolean Tipo) {
        if (Tipo == tipo) {
            TipoProducto = "Manufacturado";
        } else {
            TipoProducto = "Insumo";
        }
    }
}
