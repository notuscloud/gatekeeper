package fr.notuscloud.meetup.gatekeeper.secret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.vault.authentication.SessionManager;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("secrets")
public class SecretController {

    private SessionManager sessionManager;

    @Value("${spring.datasource.username}")
    private String dbUser;

    // secret service singleton
    private SecretService secretService;

    // Constructor
    public SecretController(SecretService secretService, SessionManager sessionManager) {
        this.secretService = secretService;
        this.sessionManager = sessionManager;
    }

    // Get all secrets
    @GetMapping("")
    public @ResponseBody Iterable<Secret> getAllSecrets(){
        return secretService.getAllSecrets();
    }

    // Add a new secrets
    @PutMapping("")
    public @ResponseBody Secret addSecret(@RequestBody Secret s){
        // encrypt clearText
        s.setSecret(secretService.encrypt(s.getClearText()));
        // return result
        return secretService.addSecret(s);
    }

    // Get secret details
    @GetMapping("/{secretId}")
    public Optional<Secret> getSecret(@PathVariable("secretId") Integer id){
        return secretService.getSecret(id);
    }

    // Del secret

    // decrypt secret
    @GetMapping("/{id}/decrypt")
    public String decryptSecret(@PathVariable Integer id){
        return secretService.decryptSecret(id);
    }

    @GetMapping("debug")
    public String debug(){
        String sessionToken = sessionManager.getSessionToken().getToken();
        return "Token:" + sessionToken + ", dbUser:" + dbUser;
    }
}
