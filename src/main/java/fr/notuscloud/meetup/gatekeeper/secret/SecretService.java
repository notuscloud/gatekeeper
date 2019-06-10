package fr.notuscloud.meetup.gatekeeper.secret;

import fr.notuscloud.meetup.gatekeeper.crypto.CryptoService;
import fr.notuscloud.meetup.gatekeeper.elasticsearch.DecryptRecord;
import fr.notuscloud.meetup.gatekeeper.elasticsearch.DecryptRecordService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SecretService {

    // Singletons
    private SecretRepository secretRepository;
    private DecryptRecordService decryptRecordService;
    private CryptoService cryptoService;

    // constructor
    public SecretService(SecretRepository secretRepository,DecryptRecordService decryptRecordService, CryptoService cryptoService) {
        this.secretRepository = secretRepository;
        this.decryptRecordService = decryptRecordService;
        this.cryptoService = cryptoService;
    }

    // list all
    public Iterable<Secret> getAllSecrets(){
        return secretRepository.findAll();
    }

    // get
    public Optional<Secret> getSecret(Integer id){
        return secretRepository.findById(id);
    }

    // add
    public Secret addSecret(Secret s){
        return secretRepository.save(s);
    }

    // delete

    // decrypt
    public String decryptSecret(Integer id){

        Optional<Secret> result = this.getSecret(id);

        if (result.isPresent()){

            Secret secret = result.get();

            // Workflow would be
            // Try to decrypt then catch result (success or failed)
            // Then log the attempt in Elasticsearch

            // Decrypt
            String decryptResult = this.cryptoService.decrypt(secret.getSecret());

            // Log decrypt attempt to ES
            DecryptRecord decryptRecord = new DecryptRecord();
            decryptRecord.setId(UUID.randomUUID().toString());
            decryptRecord.setSecretId(secret.getId().toString());
            decryptRecord.setSecretDescription(secret.getDescription());
            String indexingResult = decryptRecordService.indexDecryptRecord(decryptRecord);

            return "decrypt: " + decryptResult + ", " + indexingResult ;

        } else {

            return "Error! secret id:" + id + " not found.";

        }

    }

    // encrypt
    public String encrypt(String s){
        return cryptoService.encrypt(s);
    }
}
