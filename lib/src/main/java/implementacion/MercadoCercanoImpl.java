package implementacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class MercadoCercanoImpl {

	public static String obtenerUbicacionActual() throws IOException {
        // Abrir una URL que obtiene la ubicación actual a través de un servicio de geolocalización en línea
        URL url = new URL("https://ipinfo.io/json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject json = new JSONObject(response.toString());
        String coordenadas = json.getString("loc");

        return coordenadas;
    }

    public static String buscarMercadoMasCercano(String coordenadas, List<String> nombresDeMercados) throws IOException {
        String mercadoCercano = "";
        double distanciaMinima = Double.MAX_VALUE;
        String nombreMercadoMasCercano = "";

        for (String nombreDelMercado : nombresDeMercados) {
            // Realizar una solicitud de búsqueda del mercado en OpenStreetMap cerca de la ubicación actual
            URL url = new URL("https://nominatim.openstreetmap.org/search?format=json&amenity="+ nombreDelMercado + "&city=Buenos%20Aires&" + coordenadas);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONArray resultados = new JSONArray(response.toString());

            // Encontrar el mercado más cercano
            for (int i = 0; i < resultados.length(); i++) {
                JSONObject resultado = resultados.getJSONObject(i);
                String latitudLongitud = resultado.getString("lat") + "," + resultado.getString("lon");

                // Calcular la distancia entre tu ubicación actual y el lugar encontrado
                double distancia = calcularDistancia(coordenadas, latitudLongitud);

                // Actualizar el mercado más cercano si es más cercano que el anterior
                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    mercadoCercano = resultado.getString("display_name");
                    nombreMercadoMasCercano = nombreDelMercado;
                }
            }
        }
        System.out.println("El mercado más cercano es: " + mercadoCercano);
        return nombreMercadoMasCercano;
    }

    public static double calcularDistancia(String coordenadas1, String coordenadas2) {       
        String[] coords1 = coordenadas1.split(",");
        String[] coords2 = coordenadas2.split(",");

        double lat1 = Double.parseDouble(coords1[0]);
        double lon1 = Double.parseDouble(coords1[1]);
        double lat2 = Double.parseDouble(coords2[0]);
        double lon2 = Double.parseDouble(coords2[1]);
        
        double distancia = Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2));
        return distancia;
    }
}
