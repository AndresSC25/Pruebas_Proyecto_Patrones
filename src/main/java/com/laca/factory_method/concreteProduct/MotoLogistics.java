package com.laca.factory_method.concreteProduct;

import com.laca.factory_method.abstractCreator.LogisticsCompany;
import com.laca.factory_method.abstractProduct.Transport;
import com.laca.factory_method.contreteCreator.Moto;

public class MotoLogistics implements LogisticsCompany {
    public MotoLogistics() {
    }

    public Transport createTransport() {
        return new Moto();
    }
}
