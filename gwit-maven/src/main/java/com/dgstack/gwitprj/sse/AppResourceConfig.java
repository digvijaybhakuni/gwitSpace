package com.dgstack.gwitprj.sse;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by digvijayb on 15/01/17.
 */
public class AppResourceConfig extends ResourceConfig {
    public AppResourceConfig() {
        packages(true, "com.dgstack.gwitprj.sse.res");
    }
}
