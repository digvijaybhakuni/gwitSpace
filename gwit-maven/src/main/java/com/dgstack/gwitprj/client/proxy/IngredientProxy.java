package com.dgstack.gwitprj.client.proxy;

import com.dgstack.gwitprj.server.model.Ingredient;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

/**
 * Created by digvijayb on 15/01/17.
 */
@ProxyFor(Ingredient.class)
public interface IngredientProxy extends ValueProxy {
    public String getName();

    public void setName(String name);

    public boolean isVegan();

    public void setVegan(boolean vegan);
}
