package com.dgstack.gwitprj.server.dao.impl;

import com.dgstack.gwitprj.server.dao.PizzaDAO;
import com.dgstack.gwitprj.server.model.Ingredient;
import com.dgstack.gwitprj.server.model.Pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

/**
 * Created by digvijayb on 15/01/17.
 */
public class PizzaDAOImpl implements PizzaDAO {

    private static final List<Pizza> PIZZA_LIST = new ArrayList<>(10);

    private static final AtomicLong counter = new AtomicLong(0);

    private static void checkPizzaAttributes(Pizza pizza) {
        if (isEmpty(pizza.getName())) {
            throw new IllegalArgumentException("pizza name must not be null");
        }
    }

    private static void checkIngredientAttributes(List<Ingredient> ingredients) {
        if (ingredients.size() == 0) {
            throw new IllegalArgumentException("pizza cannot be empty");
        }
        for (Ingredient ingredient : ingredients) {
            if (isEmpty(ingredient.getName())) {
                throw new IllegalArgumentException("ingredients should have a name, too");
            }
        }
    }

    private static Predicate<Pizza> matchWithId(final Long id){
        return e-> e.getId() == id;
    }

    private static Predicate<Pizza> notNull(){
        return e-> e != null;
    }

    private static boolean isEmpty(String name) {
        return name == null || name.length() == 0;
    }

    @Override
    public Pizza findById(Long id) {
        return PIZZA_LIST.stream().filter(notNull().and(matchWithId(id))).findFirst().orElse(null);
    }

    @Override
    public void save(Pizza pizza) {
        checkPizzaAttributes(pizza);
        checkIngredientAttributes(pizza.getIngredients());
        pizza.setId(counter.incrementAndGet());
        PIZZA_LIST.add(pizza);
        PIZZA_LIST.stream().map(Pizza::getId).forEach(System.out::println);
    }
}
