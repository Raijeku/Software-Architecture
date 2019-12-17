/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.server;

import com.udea.tablerows.Figure;
import com.udea.tablerows.FigureDecoder;
import com.udea.tablerows.FigureEncoder;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Davquiroga
 */
@ServerEndpoint(value="/echo",
        encoders={FigureEncoder.class},
        decoders={FigureDecoder.class}
)
public class MyWhiteboard {
    private static Set<Session> peers=Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void broadcastFigure(Figure figure, Session session) throws IOException, EncodeException{
        for(Session peer:peers){
            if(!peer.equals(session)){
                peer.getBasicRemote().sendObject(figure);
            }
        }
    }

    @OnOpen
    public void onOpen(Session p) {
        peers.add(p);
    }

    @OnClose
    public void onClose(Session p) {
        peers.remove(p);
    }
}
