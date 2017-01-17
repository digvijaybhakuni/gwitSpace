package com.dgstack.gwitprj.server.dao;

import com.dgstack.gwitprj.server.model.Pizza;

/**
 * Created by digvijayb on 15/01/17.
 */
public interface PizzaDAO {

    Pizza findById(Long id);

    void save(Pizza pizza);
}
