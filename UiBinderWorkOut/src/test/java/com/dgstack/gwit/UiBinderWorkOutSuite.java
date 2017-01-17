package com.dgstack.gwit;

import com.dgstack.gwit.client.UiBinderWorkOutTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class UiBinderWorkOutSuite extends GWTTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("Tests for UiBinderWorkOut");
		suite.addTestSuite(UiBinderWorkOutTest.class);
		return suite;
	}
}
