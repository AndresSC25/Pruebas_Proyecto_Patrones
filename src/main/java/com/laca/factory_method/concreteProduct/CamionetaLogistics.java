package com.laca.factory_method.concreteProduct;

import com.laca.factory_method.abstractCreator.LogisticsCompany;
import com.laca.factory_method.abstractProduct.Transport;
import com.laca.factory_method.contreteCreator.Camioneta;

public class CamionetaLogistics implements LogisticsCompany {
    public CamionetaLogistics() {
    }

    public Transport createTransport() {
        return new Camioneta();
    }
}
