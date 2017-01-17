package com.dgstack.gwitprj.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>ConvertorService</code>.
 */
public interface ConvertorServiceAsync {
  void convertOnServer(String input, String cur, AsyncCallback<String> callback)
      throws IllegalArgumentException;
}
