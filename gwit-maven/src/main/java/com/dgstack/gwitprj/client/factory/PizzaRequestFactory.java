package com.dgstack.gwitprj.client.factory;

import com.dgstack.gwitprj.client.proxy.PizzaProxy;
import com.dgstack.gwitprj.server.dao.PizzaDAO;
import com.dgstack.gwitprj.server.dao.impl.DAOLocator;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

/**
 * Created by digvijayb on 15/01/17.
 */
public interface PizzaRequestFactory extends RequestFactory {

    @Service(value = PizzaDAO.class, locator = DAOLocator.class)
    public interface PizzaRequestContext extends RequestContext {
        Request<PizzaProxy> findById( Long id );
        Request<Void> save(PizzaProxy pizza );
    }

    PizzaRequestContext context();

}
