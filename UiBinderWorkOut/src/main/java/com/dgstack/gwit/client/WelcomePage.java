/**
 * 
 */
package com.dgstack.gwit.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

/**
 * @author digvijayb
 *
 */
public class WelcomePage extends UIObject {

	private static WelcomePageUiBinder uiBinder = GWT.create(WelcomePageUiBinder.class);

	interface WelcomePageUiBinder extends UiBinder<Element, WelcomePage> {
	}

	
	@UiField
	SpanElement nameSpan;
	
//	@UiField
//	Button mybutton;
	
//	@UiField
//	TextBox mytextBox;

	public WelcomePage(String firstName) {
		setElement(uiBinder.createAndBindUi(this));

		// Can access @UiField after calling createAndBindUi
		nameSpan.setInnerText(firstName);
	}
	

	
	public String getMyTextBoxVal(){
		return "Hello";//mytextBox.getValue();
	}
	
	
	
}
