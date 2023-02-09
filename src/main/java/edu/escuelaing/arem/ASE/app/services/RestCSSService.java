package edu.escuelaing.arem.ASE.app.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RestCSSService implements RestService {

    @Override
    public String getHeader() {
        return "HTTP/1.1 200 \r\n" +
                "Content-Type: text/css \r\n" +
                "\r\n";
    }

    @Override
    public String getResponse() {
        byte[] file;

        try {
            file = Files.readAllBytes(Paths.get("src/main/resources/style.css"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(file);
    }

}
