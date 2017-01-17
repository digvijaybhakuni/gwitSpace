package com.dgstack.gwtcurex;

import com.dgstack.gwtcurex.client.Gwt_currency_excanhgeTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class Gwt_currency_excanhgeSuite extends GWTTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("Tests for Gwt_currency_excanhge");
		suite.addTestSuite(Gwt_currency_excanhgeTest.class);
		return suite;
	}
}
