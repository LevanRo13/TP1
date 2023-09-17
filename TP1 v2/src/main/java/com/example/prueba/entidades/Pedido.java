package com.example.prueba.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Pedido extends BaseEntidad{

    private boolean estado;
    private Date fecha;
    private boolean tipoEnvio = true;
    private double total;
    private boolean iniciado;
    private boolean preparacion;
    private boolean entregado;
    private String estadoActual;
    private String envio;

    @OneToOne(cascade = CascadeType.ALL, optional = true, orphanRemoval = true, fetch = FetchType.LAZY)
    //Se pone FetchType.LAZY porque como la relacion es 0..1, no siempre me pide la  factura
    @JoinColumn(nullable = true)
    // @Builder.Default
    private Factura factura;

    //Se establece el estado del pedido
    public void setIniciado(){
        iniciado=true;
        preparacion=false;
        entregado=false;
    }

    public void setPreparacion(){
        iniciado=false;
        preparacion=true;
        entregado=false;
    }

    public void setEntregado(){
        iniciado=false;
        preparacion=false;
        entregado=true;
    }


    //Comprobar el estado actual del pedido y devolverlo como parámetro

    public void mostrarEstado() {
        if (iniciado == true) {
            estadoActual = "Iniciado";
        }
        if (preparacion == true) {
            estadoActual = "En preparación";
        }
        if (entregado == true) {
            estadoActual = "Entregado";
        }
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "Pedido_id")
    @Builder.Default
    private List<DetallePedido> detalle = new ArrayList<>();

    //Setear el tipo de envío

    public void retiro(boolean Envio) {
        if(Envio==tipoEnvio){
            envio="Retiro en el local";
        }else {
            envio="Envío por Delivery";
        }
    }
}
