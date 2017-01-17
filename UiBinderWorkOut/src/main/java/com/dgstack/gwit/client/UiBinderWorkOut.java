package com.dgstack.gwit.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class UiBinderWorkOut implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		WelcomePage welcomePage = new WelcomePage("Diggu");
		LoginForm lgnFrm = new LoginForm("Diggu");
		
		welcomePage.setTitle("Welcome Page");
		lgnFrm.addBtnClickEvnt(e -> {greetingService.greetServer(welcomePage.getMyTextBoxVal(), openDialog()); return null;});
//		welcomePage.addMyButtonClickHndlr(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				greetingService.greetServer(welcomePage.getMyTextBoxVal(), openDialog());
//			}
//
//			
//		});
		
		
		Document.get().getBody().appendChild(welcomePage.getElement());
		RootPanel.get().add(lgnFrm);
		
	}
	
	private AsyncCallback<String> openDialog() {
		return new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				final DialogBox dbx = new DialogBox(false, false);
				dbx.setText("Success");
				dbx.add(new HTML("Result from Server " + result));
				dbx.center();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				final DialogBox dbx = new DialogBox(false, false);
				dbx.setText("Failure");
				dbx.add(new HTML("Result from Server " + caught.getMessage()));
				dbx.center();
			}
		};
	}
}
