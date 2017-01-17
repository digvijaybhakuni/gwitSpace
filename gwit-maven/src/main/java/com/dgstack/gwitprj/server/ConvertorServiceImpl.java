package com.dgstack.gwitprj.server;

import com.dgstack.gwitprj.client.ConvertorService;
import com.dgstack.gwitprj.shared.FieldVerifier;
import com.dgstack.gwitprj.sse.BroadcasterFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ConvertorServiceImpl extends RemoteServiceServlet implements
        ConvertorService {


  public String convertOnServer(String input, String cur) throws IllegalArgumentException {
    // Verify that the input is valid. 
    if (!FieldVerifier.isValidNumber(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "It should be number");
    }

    String serverInfo = getServletContext().getServerInfo();
    String userAgent = getThreadLocalRequest().getHeader("User-Agent");
    double inputVal  = Double.parseDouble(input);
    double rate = rate(cur);
    double outputVal = inputVal * rate;
    // Escape data from the client to avoid cross-site script vulnerabilities.
    input = escapeHtml(input);
    userAgent = escapeHtml(userAgent);
    String message = "USD " + inputVal + " is <strong style='color:red;'>"+String.format("%.2f %s", outputVal, cur)+" </strong> ";
    BroadcasterFactory.factory().emmit(message);
    return message + "<br><br>I am running " + serverInfo
        + ".<br><br>It looks like you are using:<br>" + userAgent;
  }

  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   * 
   * @param html the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(String html) {
    if (html == null) {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
        ">", "&gt;");
  }


  private Double rate(String cur){
    Double rate = null;
    switch (cur){
      case "INR":
        rate = 67.9669;
        break;
      case "EUR":
      rate = 0.950705;
        break;
      case "GBP":
      rate = 0.810435;
        break;
      case "JPY":
        rate = 116.890747;
        break;
      case "RUB":
        rate = 61.2663;
        break;
      case "CAD":
        rate = 1.343851;
        break;
      case "AUD":
        rate = 1.3873;
        break;
      default:
        rate = 1D;
        break;
    }
    return rate;
  }
}
