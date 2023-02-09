package edu.escuelaing.arem.ASE.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {

    private static final String userAgent = "Mozilla/5.0";
    private static String urlGetUrl = "http://www.omdbapi.com/?t=";
    private static final String urlApiKey = "&apikey=b63096b1";

    /**
     * Method that obtains the String of the information of the movie
     * @param title Title of movie
     * @return title of the movie
     * @throws IOException class exception
     */
    public static String requestTitle(String title) throws IOException {
        //Cache
        Cache cache = Cache.getInstance();
        if(cache.isOnCache(title)){
            System.out.println("Se uso el cache");
            return cache.getMovieDescription(title);
        }
        //Connection Api Cinema
        String urlComplete = urlGetUrl + title + urlApiKey;
        URL obj = new URL(urlComplete);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", userAgent);
        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String res = "[" + response.toString() + "]" ;
            cache.addMovie(title, res);
            return res;
        } else {
            System.out.println("GET request not worked");
        }
        return "Failed";
    }

}