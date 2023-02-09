package edu.escuelaing.arem.ASE.app.webapps;

import edu.escuelaing.arem.ASE.app.HttpServer;
import edu.escuelaing.arem.ASE.app.services.*;

import java.io.IOException;

public class FirstApp {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        HttpServer server = HttpServer.getInstance();
        server.addService("/index", new RestHTMLService());
        server.addService("/app", new RestJSService());
        server.addService("/style", new RestCSSService());
        server.addService("/image", new RestJPGService());
        server.run(args);
    }
}
