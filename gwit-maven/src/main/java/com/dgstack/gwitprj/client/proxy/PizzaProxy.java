package com.dgstack.gwitprj.client.proxy;

import com.dgstack.gwitprj.server.dao.impl.PizzaLocator;
import com.dgstack.gwitprj.server.model.Pizza;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

import java.util.List;

/**
 * Created by digvijayb on 15/01/17.
 */
@ProxyFor(value = Pizza.class, locator = PizzaLocator.class)
public interface PizzaProxy extends EntityProxy {

    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public List<IngredientProxy> getIngredients();

    public void setIngredients(List<IngredientProxy> ingredients);
}
