package edu.escuelaing.arem.ASE.app.webapps;

import edu.escuelaing.arem.ASE.app.HttpServer;
import edu.escuelaing.arem.ASE.app.services.*;

import java.io.IOException;

public class FirstApp {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        HttpServer server = HttpServer.getInstance();
        server.addService("/index.html", new RestHTMLService());
        server.addService("/app.js", new RestJSService());
        server.addService("/style.css", new RestCSSService());
        server.addService("/imagen.jpg", new RestJPGService());
        server.addService("/pagina.html", new RestPaginaService());
        server.addService("/404", new RestNotFoundService());
        server.run(args);
    }
}
