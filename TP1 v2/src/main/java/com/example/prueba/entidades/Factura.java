package com.example.prueba.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura extends BaseEntidad{

    private int numero;
    private Date fecha;
    private double descuento;
    private boolean formaPago = true;
    private int total;
    private String pago;


    public void retiro(boolean FormaPago) {
        if (FormaPago == formaPago) {
            pago = "Efectivo";
        } else {
            pago = "Otros medios de pago";
        }
    }
}
