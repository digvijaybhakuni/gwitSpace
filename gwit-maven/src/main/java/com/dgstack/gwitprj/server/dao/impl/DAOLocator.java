package com.dgstack.gwitprj.server.dao.impl;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * Created by digvijayb on 15/01/17.
 */
public class DAOLocator implements ServiceLocator {

    @Override
    public Object getInstance(Class<?> aClass) {
        return new PizzaDAOImpl();
    }
}
