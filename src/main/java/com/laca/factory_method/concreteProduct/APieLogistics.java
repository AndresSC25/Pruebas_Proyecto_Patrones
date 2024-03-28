package com.laca.factory_method.concreteProduct;

import com.laca.factory_method.abstractCreator.LogisticsCompany;
import com.laca.factory_method.abstractProduct.Transport;
import com.laca.factory_method.contreteCreator.APie;

public class APieLogistics implements LogisticsCompany {
    public APieLogistics() {
    }

    public Transport createTransport() {
        return new APie();
    }
}
