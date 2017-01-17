package com.dgstack.gwitprj.sse;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;

import javax.ws.rs.core.MediaType;

/**
 * Created by digvijayb on 15/01/17.
 */
public class BroadcasterFactory {

    private final SseBroadcaster broadcaster = new SseBroadcaster();

    private final static BroadcasterFactory factory = new BroadcasterFactory();

    private BroadcasterFactory(){}

    public static BroadcasterFactory factory(){
        return factory;
    }

    public void addEventClient(EventOutput output){
        broadcaster.add(output);
    }

    public void emmit(String message){
        OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
        OutboundEvent event = eventBuilder.name("message").mediaType(MediaType.TEXT_HTML_TYPE)
                .data(String.class, message).build();

        broadcaster.broadcast(event);
    }
}
