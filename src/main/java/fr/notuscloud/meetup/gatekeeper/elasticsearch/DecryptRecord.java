package fr.notuscloud.meetup.gatekeeper.elasticsearch;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DecryptRecord {
    private String id;
    private String secretId;
    private String secretDescription;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSecretId() {
        return secretId;
    }
    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }
    public String getSecretDescription() {
        return secretDescription;
    }
    public void setSecretDescription(String secretDescription) {
        this.secretDescription = secretDescription;
    }
}
