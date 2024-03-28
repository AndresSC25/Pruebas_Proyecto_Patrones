package com.laca.factory_method.contreteCreator;

import com.laca.factory_method.abstractProduct.Transport;

public class Moto implements Transport {
    //    CREATE TABLE TBL_UNIDAD_TRANSPORTE(
//            id_unidad INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
//            id_usuario INT NOT NULL,
//            tipo_unidad VARCHAR(100) NOT NULL,
//    capacidad INT NOT NULL,
//    precio DECIMAL(10,2) NOT NULL,
//    estado VARCHAR(50) NOT NULL,
//    FOREIGN KEY (id_usuario) REFERENCES TBL_USUARIO(id_usuario)
//            );
    long id_unidad;
    long id_usuario;
    String tipo_unidad;
    int capacidad;
    double precio;
    String estado;

    public Moto() {

    }

    public Moto(long id_unidad, long id_usuario, String tipo_unidad, int capacidad, double precio, String estado) {
        this.id_unidad = id_unidad;
        this.id_usuario = id_usuario;
        this.tipo_unidad = tipo_unidad;
        this.capacidad = capacidad;
        this.precio = precio;
        this.estado = estado;
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public long getId_unidad() {
        return id_usuario;
    }

    public void setId_unidad(long id_unidad) {
        this.id_usuario = id_unidad;
    }

    public String getTipo_unidad() {
        return tipo_unidad;
    }

    public void setTipo_unidad(String tipo_unidad) {
        this.tipo_unidad = tipo_unidad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

