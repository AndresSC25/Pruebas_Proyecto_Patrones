package com.laca.factory_method.concreteProduct;

import com.laca.factory_method.abstractCreator.LogisticsCompany;
import com.laca.factory_method.abstractProduct.Transport;
import com.laca.factory_method.contreteCreator.Camion;

public class CamionLogistics implements LogisticsCompany {
    public CamionLogistics() {
    }

    public Transport createTransport() {
        return new Camion();
    }
}
