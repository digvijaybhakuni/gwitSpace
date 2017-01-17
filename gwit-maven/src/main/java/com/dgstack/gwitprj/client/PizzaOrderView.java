package com.dgstack.gwitprj.client;

import com.dgstack.gwitprj.client.factory.PizzaRequestFactory;
import com.dgstack.gwitprj.client.proxy.IngredientProxy;
import com.dgstack.gwitprj.client.proxy.PizzaProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Created by digvijayb on 15/01/17.
 */
public class PizzaOrderView extends VerticalPanel {

    private Button sendButton;
    private TextBox nameField;
    private Label errorLabel;
    private TextBox ingredientField;
    private Label displayLbl;
    private TextBox findField;

    private long id = 0L;

    public PizzaOrderView() {

        sendButton = new Button("Order pizza");
        nameField = new TextBox();
        nameField.setText("Pizza Name");
        ingredientField = new TextBox();
        ingredientField.setText("Ingredient");
        errorLabel = new Label();
        displayLbl = new Label();
        findField = new TextBox();

        final HorizontalPanel horizontalPanel = new HorizontalPanel();
        findUI(horizontalPanel, findField);

        sendButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                orderPizza();
            }
        });

        add(nameField, ingredientField, sendButton, errorLabel, horizontalPanel);

        nameField.setFocus(true);
    }

    private static void findUI(final HorizontalPanel panel, final TextBox findField) {
        panel.add(new Label("Id"));
        panel.add(findField);
        panel.add(new Button("Find", new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                long id = Long.parseLong(findField.getText());
                Request<PizzaProxy> reqPizza = createFactory().context().findById(id).with("ingredients");
                //Request<Person> findReq = find(personId).with("phone","address.city","address.zip")//For multiple nested
                reqPizza.fire(new Receiver<PizzaProxy>() {
                    @Override
                    public void onSuccess(PizzaProxy pizzaProxy) {
                        final DialogBox dialogBox = new DialogBox();
                        dialogBox.setModal(true);
                        dialogBox.setText("Pizza Finder");
                        VerticalPanel verticalPanel = new VerticalPanel();
                        dialogBox.add(verticalPanel);
                        if (pizzaProxy != null) {
                            final StringBuilder builder = new StringBuilder("<b>Pizza Order Found</b> <br/>");
                            builder.append("<b>Pizza Name ").append(pizzaProxy.getName()).append("</b> <br/>");
                            if (pizzaProxy.getIngredients() != null) {
                                builder.append("<b>Ingredients </b> <ul>");
                                pizzaProxy.getIngredients().forEach(new Consumer<IngredientProxy>() {
                                    @Override
                                    public void accept(IngredientProxy e) {
                                        builder.append("<li>").append(
                                                e.getName()).append("</li>");
                                    }
                                });
                                builder.append("</ul>");
                            }
                            verticalPanel.add(new HTML(builder.toString()));
                        } else {
                            verticalPanel.add(new HTML("<b>Cannot find the Pizza Order</b>"));
                        }
                        verticalPanel.add(new Button("Close", new ClickHandler() {
                            @Override
                            public void onClick(ClickEvent clickEvent) {
                                dialogBox.hide();
                            }
                        }));
                        dialogBox.center();
                    }
                });
            }
        }));
    }

    private static PizzaRequestFactory createFactory() {
        PizzaRequestFactory factory = GWT.create(PizzaRequestFactory.class);
        factory.initialize(new SimpleEventBus());
        return factory;
    }

    private static DialogBox createConfirmationDialogBox() {
        final DialogBox dialogBox = new DialogBox();
        dialogBox.setModal(true);
        dialogBox.setText("Request Factory Call");
        dialogBox.setAnimationEnabled(true);
        final Button closeButton = new Button("Close");
        // We can set the id of a widget by accessing its Element
        closeButton.getElement().setId("closeButton");
        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.addStyleName("dialogVPanel");
        dialogVPanel.add(new HTML("<b>Thanks for your order</b>"));
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        dialogVPanel.add(closeButton);
        dialogBox.setWidget(dialogVPanel);

        // Add a handler to close the DialogBox
        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });
        return dialogBox;
    }

    private void add(Widget... widgets) {
        if (widgets != null) {
            for (Widget w : widgets) {
                add(w);
            }
        }
    }

    private void orderPizza() {

        PizzaRequestFactory.PizzaRequestContext context = createFactory().context();
        PizzaProxy pizza = context.create(PizzaProxy.class);
        pizza.setName(nameField.getText());
        //pizza.setId(++id);
        IngredientProxy ingredient = context.create(IngredientProxy.class);
        ingredient.setName(ingredientField.getText());
        pizza.setIngredients(Arrays.asList(ingredient));

        context.save(pizza).fire(new Receiver<Void>() {
            @Override
            public void onSuccess(Void v) {
                createConfirmationDialogBox().center();
                sendButton.setEnabled(true);
            }

            @Override
            public void onFailure(ServerFailure error) {
                errorLabel.setText(error.getMessage());
                sendButton.setEnabled(true);
            }
        });

    }
}
