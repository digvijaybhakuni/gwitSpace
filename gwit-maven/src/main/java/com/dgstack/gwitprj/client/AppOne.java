package com.dgstack.gwitprj.client;

import com.dgstack.gwitprj.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AppOne implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";


  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final ConvertorServiceAsync convertorService = GWT.create(ConvertorService.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    final HorizontalPanel inputPanel  = new HorizontalPanel();
    final Label errorLabel = new Label();

    // Add the nameField and convertButton to the RootPanel
    // Use RootPanel.get() to get the entire body element
    RootPanel.get("inputContainer").add(inputPanel);
    RootPanel.get("errorLabelContainer").add(errorLabel);
    RootPanel.get("reqFactoryContainer").add(new PizzaOrderView());

    // Focus the cursor on the name field when the app loads
    final Label lbl = new Label("US Doller : ");
    final Button convertButton = new Button("Convert");
    final TextBox amtField = new TextBox();
    final ListBox currencyList = new ListBox();
    currencyList.setMultipleSelect(false);
    currencyList.setHeight("100%");
    currencyList.addItem("India National Rupee - INR", "INR");
    currencyList.addItem("Great Britain Pond - GBP", "GBP");
    currencyList.addItem("Japanese Yen - JPY", "JPY");
    currencyList.addItem("Euro - EUR", "EUR");
    currencyList.addItem("Russia Ruble - RUB", "RUB");
    currencyList.addItem("Canadian Dollar - CAD", "CAD");
    currencyList.addItem("Australian Dollar - AUD", "AUD");

    inputPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    inputPanel.add(lbl);
    inputPanel.add(amtField);
    inputPanel.add(currencyList);
    inputPanel.add(convertButton);

    amtField.setText("");

    amtField.setFocus(true);
    amtField.selectAll();

    // Create the popup dialog box
    final DialogBox dialogBox = new DialogBox();

    dialogBox.setText("Remote Procedure Call");
    dialogBox.setAnimationEnabled(true);
    final Button closeButton = new Button("Close");
    // We can set the id of a widget by accessing its Element
    closeButton.getElement().setId("closeButton");
    final Label textToServerLabel = new Label();
    final HTML serverResponseLabel = new HTML();

    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");
    dialogVPanel.add(new HTML("<b>Send to the server:</b>"));
    dialogVPanel.add(textToServerLabel);
    dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
    dialogVPanel.add(serverResponseLabel);
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
    dialogVPanel.add(closeButton);
    dialogBox.setWidget(dialogVPanel);

    // Add a handler to close the DialogBox
    closeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogBox.hide();
        convertButton.setEnabled(true);
        convertButton.setFocus(true);
      }
    });

    // Create a handler for the convertButton and nameField
    class MyHandler implements ClickHandler, KeyUpHandler {
      /**
       * Fired when the user clicks on the convertButton.
       */
      public void onClick(ClickEvent event) {
        doItOnToServer();
      }

      /**
       * Fired when the user types in the nameField.
       */
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          doItOnToServer();
        }
      }

      /**
       * Send the name from the nameField to the server and wait for a response.
       */
      private void doItOnToServer() {
        // First, we validate the input.
        errorLabel.setText("");
        String textToServer = amtField.getText();
        if (!FieldVerifier.isValidNumber(textToServer)) {
          errorLabel.setText("Please enter Number only");
          return;
        }
        String cur = currencyList.getSelectedValue();
        // Then, we send the input to the server.
        convertButton.setEnabled(false);
        textToServerLabel.setText("Convert " + textToServer + " USD to "+cur);
        serverResponseLabel.setText("");
        convertorService.convertOnServer(textToServer, cur,new AsyncCallback<String>() {
          public void onFailure(Throwable caught) {
            // Show the RPC error message to the user
            dialogBox.setText("Remote Procedure Call - Failure");
            serverResponseLabel.addStyleName("serverResponseLabelError");
            serverResponseLabel.setHTML(SERVER_ERROR);
            dialogBox.center();
            closeButton.setFocus(true);
          }

          public void onSuccess(String result) {
            dialogBox.setText("Remote Procedure Call");
            serverResponseLabel.removeStyleName("serverResponseLabelError");
            serverResponseLabel.setHTML(result);
            dialogBox.center();
            closeButton.setFocus(true);
          }
        });
      }
    }

    // Add a handler to send the name to the server
    MyHandler handler = new MyHandler();
    convertButton.addClickHandler(handler);
    amtField.addKeyUpHandler(handler);
  }
}
