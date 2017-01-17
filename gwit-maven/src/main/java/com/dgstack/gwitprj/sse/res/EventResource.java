package com.dgstack.gwitprj.sse.res;

import com.dgstack.gwitprj.sse.BroadcasterFactory;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by digvijayb on 15/01/17.
 */
@Singleton
@Path("event")
public class EventResource {

    @GET
    @Path("sse")
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput poolEvents(){
        final EventOutput eventOutput = new EventOutput();
        BroadcasterFactory.factory().addEventClient(eventOutput);
        return eventOutput;
    }

    @GET @Path("test")
    public String test(){
        return "TEST";
    }

}
