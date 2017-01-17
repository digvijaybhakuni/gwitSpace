package com.dgstack.gwitprj.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("convertor")
public interface ConvertorService extends RemoteService {
  String convertOnServer(String input, String cur) throws IllegalArgumentException;
}
