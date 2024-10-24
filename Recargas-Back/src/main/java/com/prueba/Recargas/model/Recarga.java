package com.prueba.Recargas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "recargas")
public class Recarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal cantidad;

    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "operador_id", nullable = false)
    private Operador operador;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @Column(name = "fecha_venta", updatable = false)
    private LocalDateTime fechaVenta;

    public Recarga() {
    }

    public Recarga(Long id, BigDecimal cantidad, BigDecimal valor, Operador operador, Vendedor vendedor,
            LocalDateTime fechaVenta) {
        this.id = id;
        this.cantidad = cantidad;
        this.valor = valor;
        this.operador = operador;
        this.vendedor = vendedor;
        this.fechaVenta = fechaVenta;
    }

    public Recarga(Long id, BigDecimal cantidad, BigDecimal valor, Operador operador, Vendedor vendedor) {
        this.id = id;
        this.cantidad = cantidad;
        this.valor = valor;
        this.operador = operador;
        this.vendedor = vendedor;        
        this.fechaVenta = LocalDateTime.now(); // Establecer la fecha actual

    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
}
