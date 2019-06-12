package fr.notuscloud.meetup.gatekeeper.elasticsearch;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DecryptRecord {

    private String timestamp;
    private String id;
    private String secretId;
    private String secretDescription;

    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.FRENCH);
        Date now = new Date();
        this.timestamp = format.format(now);
    }
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
