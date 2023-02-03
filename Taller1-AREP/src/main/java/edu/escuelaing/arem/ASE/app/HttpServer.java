package edu.escuelaing.arem.ASE.app;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.json.*;

public class HttpServer {

    private static Map<String, RestService> services = new HashMap<>();

    private static  HttpServer _instance = new HttpServer();

    public static HttpServer getInstance() {
        return _instance;
    }

    public static void main(String[] args) throws IOException {

        String urlTitle = "";
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while(running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream()));
            String inputLine, outputLine, stringfirstLine = "";
            Boolean boolStringLine = true;

            while ((inputLine = in.readLine()) != null) {
                if(inputLine.contains("hello?name=")){
                    System.out.println("---------------------------");
                    System.out.println(inputLine);
                    System.out.println("---------------------------");
                    String[] res = inputLine.split("name=");
                    urlTitle = (res[1].split("HTTP")[0]).replace(" ", "");
                }
                if (boolStringLine) {
                    stringfirstLine = inputLine;
                    boolStringLine = false;
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            if (stringfirstLine.startsWith("/apps/")) {
                outputLine = executeService(stringfirstLine.substring(5));
            } else {
                outputLine = htmlWithForms();
            }

            if(!urlTitle.equals("")){
                String response = HttpConnection.requestTitle(urlTitle);
                outputLine ="HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<br>"
                        + "<table border=\" 1 \"> \n " + doTable(response)
                        + "</table>";
            }else {
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + htmlWithForms();
            }

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    public static String executeService(String serviceName) {
        RestService rs = services.get(serviceName);
        String header = rs.getHeader();
        String body = rs.getResponse();
        return header + body;
    }

    public void addService(String key, RestService service) {
        services.put(key, service);
    }


    /**
     * Method that creates a table in String(JSON) format
     * @param response String movie information
     * @return String(JSON)
     */
    private static String doTable(String response){
        HashMap<String,String> dict = new HashMap<String, String>();
        JSONArray jsonArray = new JSONArray(response);
        for (int i=0; i<jsonArray.length();i++){
            JSONObject object = jsonArray.getJSONObject(i);
            for (String key: object.keySet()) {
                dict.put(key.toString(), object.get(key).toString());
            }
        }
        String table = "<tr> \n";
        for (String keys: dict.keySet()){
            String value = dict.get(keys);
            table += "<td>" + keys + "</td>\n";
            table += "<td>" + value + "</td>\n";
            table += "<tr> \n";
        }
        return table;
    }

    /**
     * Method createv view html
     * @return view html
     */
    public static String htmlWithForms(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Search Films</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Search Movies</h1>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"name\">Name:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" +
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function loadGetMsg() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                if (nameVar) {\n" +
                "                   console.log(\"Nombre \" + nameVar)\n" +
                "                   const xhttp = new XMLHttpRequest();\n" +
                "                   xhttp.onload = function() {\n" +
                "                       document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                       this.responseText;\n" +
                "                   }\n" +
                "                   xhttp.open(\"GET\", \"/hello?name=\"+nameVar);\n" +
                "                   xhttp.send();\n" +
                "                };\n" +
                "            }\n" +
                "        </script>\n" +
                "\n" +
                "    </body>\n" +
                "</html>";
    }
}