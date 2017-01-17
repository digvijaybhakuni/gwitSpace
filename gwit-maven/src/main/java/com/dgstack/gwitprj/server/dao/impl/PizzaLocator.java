package com.dgstack.gwitprj.server.dao.impl;

import com.dgstack.gwitprj.server.dao.PizzaDAO;
import com.dgstack.gwitprj.server.model.Pizza;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * Created by digvijayb on 15/01/17.
 */
public class PizzaLocator extends Locator<Pizza, Long> {

    final private PizzaDAO dao = new PizzaDAOImpl();

    @Override
    public Pizza create(Class<? extends Pizza> aClass) {
        Pizza pizza = new Pizza();
        pizza.setVersion(1L);
        return pizza;
    }

    @Override
    public Pizza find(Class<? extends Pizza> aClass, Long aLong) {
        return dao.findById(aLong);
    }

    @Override
    public Class<Pizza> getDomainType() {
        return Pizza.class;
    }

    @Override
    public Long getId(Pizza pizza) {
        return pizza.getId();
    }

    @Override
    public Class<Long> getIdType() {
        return Long.class;
    }

    @Override
    public Object getVersion(Pizza pizza) {
        return pizza.getVersion();
    }
}
