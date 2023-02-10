package edu.escuelaing.arem.ASE.app.services;

import java.io.IOException;

public interface RestService {

    /**
     * Metodo que obtiene el Header del archivo
     * @return Header en String
     */
    public String getHeader();

    /**
     * Metodo que obtiene el Body del archivo
     * @return Body en String
     */
    public String getResponse() throws IOException;

}
