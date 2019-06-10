package fr.notuscloud.meetup.gatekeeper.crypto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.Plaintext;
import org.springframework.vault.support.Ciphertext;

@Service
public class CryptoService implements CryptoInterface {

    @Value("${vault.transit.key}")
    private String encryptionKey;

    private VaultOperations vaultOperations;

    // Construction
    public CryptoService(VaultOperations vaultOperations) {
        this.vaultOperations = vaultOperations;
    }

    @Override
    public String decrypt(String s){

        Ciphertext ciphertext = Ciphertext.of(s);
        return vaultOperations.opsForTransit().decrypt(encryptionKey, ciphertext).asString();

    }

    @Override
    public String encrypt(String s) {

        Plaintext plaintext =  Plaintext.of(s);
        return vaultOperations.opsForTransit().encrypt(encryptionKey, plaintext).getCiphertext();

    }
}
