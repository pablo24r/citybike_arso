package pasarela.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final String usuarioServiceUrl = "http://localhost:5000/api/usuarios";

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String nick, @RequestParam String password) {
        // Crea el objeto RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("nick", nick);
        body.add("password", password);
        
        // Definir los encabezados de la solicitud
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        // Crear la entidad de la solicitud con los datos y los encabezados
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
 
        ResponseEntity<?> res = restTemplate.exchange(usuarioServiceUrl, HttpMethod.POST, requestEntity, String.class);
        
        System.out.println("body="+res.getBody());
        System.out.println("code="+res.getStatusCodeValue());
        
        return res;
    }

    @PostMapping("/oauth2")
    public ResponseEntity<?> loginOAuth2(@RequestParam String githubId) {
        return null;
    }
    
    @GetMapping("/prueba/{id}")
    public ResponseEntity<?> prueba(@PathVariable String id) {
	    return ResponseEntity.ok().body(("{\"message\": \"Prueba exitosa\", \"userId\": \"" + id + "\"}"));
    }
}
