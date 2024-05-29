package util;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GraphAPI {
    public static void kirimData(String urlAPI, String nama, String to) {
        try {
            // URL endpoint untuk POST request
            URL url = new URL("https://tubesprakpro.bhadrikais.my.id/"+ urlAPI);

            // Membuat HttpURLConnection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            // Data JSON body yang akan dikirim
            StringBuilder jsonDataBuilder = new StringBuilder();
            switch (urlAPI) {
                case "addVertice" :
                        jsonDataBuilder.append("{\n");
                        jsonDataBuilder.append("        \"name\": \"").append(nama).append("\"");
                        // for (int i = 0; i < vertices.size(); i++) {
                        //     // if (i < vertices.size() - 1) {
                        //     //     jsonDataBuilder.append(",");
                        //     // }
                        // }
                        jsonDataBuilder.append("\n");
                        jsonDataBuilder.append("}");
                    break;
                case "addEdge" :
                        jsonDataBuilder.append("{\n");
                        jsonDataBuilder.append("\"from\": \"").append(nama).append("\",");
                        jsonDataBuilder.append("\"to\": \"").append(to).append("\"");
                        jsonDataBuilder.append("\n");
                        jsonDataBuilder.append("}");
                        // for (int i = 0; i < vertices.size(); i++) {
                        //     // if (i < vertices.size() - 1) {
                        //     //     jsonDataBuilder.append(",");
                        //     // }
                        // }
                    break;
                case "reset" :
                    jsonDataBuilder.append("{\n");
                    jsonDataBuilder.append("\"from\": \"").append(nama).append("\",");
                    jsonDataBuilder.append("\"to\": \"").append(to).append("\"");
                    jsonDataBuilder.append("\n");
                    jsonDataBuilder.append("}");
                break;
                default:
                    throw new AssertionError();
            }


            // jsonDataBuilder.append("    \"edges\": [\n");
            // for (int i = 0; i < edges.size(); i++) {
            //     jsonDataBuilder.append("        ").append(edges.get(i));
            //     if (i < edges.size() - 1) {
            //         jsonDataBuilder.append(",");
            //     }
            //     jsonDataBuilder.append("\n");
            // }
            // jsonDataBuilder.append("    ]\n");
            // jsonDataBuilder.append("}");

            String jsonData = jsonDataBuilder.toString();

            // Mengirim data JSON body
            OutputStream os = conn.getOutputStream();
            byte[] input = jsonData.getBytes("utf-8");
            os.write(input, 0, input.length);

            // Menerima respons dari server
            int responseCode = conn.getResponseCode();
            // System.out.println("Response Code: " + responseCode);

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
