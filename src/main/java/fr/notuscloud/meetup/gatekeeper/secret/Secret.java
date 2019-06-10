package fr.notuscloud.meetup.gatekeeper.secret;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Secret {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String description;
    private String secret;
    private String clearText;

    // Getters and setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }
    public String getClearText() {
        return clearText;
    }
    public void setClearText(String clearText) {
        this.clearText = clearText;
    }

    // Classic constructor
    public Secret(){

    }
    public Secret(Integer id, String description, String secret) {
        super();
        this.id = id;
        this.description = description;
        this.secret = secret;
        this.clearText = clearText;
    }
}
