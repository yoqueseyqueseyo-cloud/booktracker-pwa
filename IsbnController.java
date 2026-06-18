package booktracker.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/isbn")
public class IsbnController {

    private final String SERP_API_KEY = "TU_API_KEY_AQUI";

    @GetMapping("/{isbn}")
    public ResponseEntity<?> buscarPorIsbn(@PathVariable String isbn) {
        try {
            String url = "https://serpapi.com/search?engine=amazon&q=" + isbn + "&api_key=" + SERP_API_KEY;

            RestTemplate rest = new RestTemplate();
            Map<String, Object> respuesta = rest.getForObject(url, Map.class);

            // Extraer resultados
            List<Map<String, Object>> resultados = (List<Map<String, Object>>) respuesta.get("organic_results");
            if (resultados == null || resultados.isEmpty()) {
                return ResponseEntity.status(404).body("No encontrado en Amazon");
            }

            Map<String, Object> libro = resultados.get(0);

            String titulo = (String) libro.get("title");
            String autor = libro.containsKey("author") ? (String) libro.get("author") : "";
            String editorial = libro.containsKey("brand") ? (String) libro.get("brand") : "";
            String portadaUrl = libro.containsKey("thumbnail") ? (String) libro.get("thumbnail") : null;

            // Convertir portada a Base64
            String portadaBase64 = null;
            if (portadaUrl != null) {
                portadaBase64 = convertirImagenABase64(portadaUrl);
            }

            Map<String, Object> resultado = new HashMap<>();
            resultado.put("titulo", titulo);
            resultado.put("autor", autor);
            resultado.put("editorial", editorial);
            resultado.put("portada", portadaBase64);

            return ResponseEntity.ok(resultado);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error procesando ISBN");
        }
    }

    private String convertirImagenABase64(String url) throws IOException {
        byte[] bytes = new URL(url).openStream().readAllBytes();
        return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bytes);
    }
}
