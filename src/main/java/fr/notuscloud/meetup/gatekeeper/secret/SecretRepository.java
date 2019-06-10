package fr.notuscloud.meetup.gatekeeper.secret;

import org.springframework.data.repository.CrudRepository;

public interface SecretRepository extends CrudRepository<Secret, Integer> {
}
