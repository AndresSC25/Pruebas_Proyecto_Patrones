package com.laca.factory_method.abstractProduct;

public interface Transport {
     long getId_usuario();

     void setId_usuario(long id_usuario);

     long getId_unidad();

     void setId_unidad(long id_unidad);

     String getTipo_unidad();

     void setTipo_unidad(String tipo_unidad);

     int getCapacidad();

     void setCapacidad(int capacidad);

     double getPrecio();

     void setPrecio(double precio);

     String getEstado();

     void setEstado(String estado);
}
