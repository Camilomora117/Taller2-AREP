package edu.escuelaing.arem.ASE.app.webapps;

import edu.escuelaing.arem.ASE.app.RestService;

public class HelloService implements RestService {


    @Override
    public String getHeader() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n";
    }

    @Override
    public String getResponse() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Search Films</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "       <h1>Hola mundo </h1>" +
                "    </body>\n" +
                "</html>";
    }
}
