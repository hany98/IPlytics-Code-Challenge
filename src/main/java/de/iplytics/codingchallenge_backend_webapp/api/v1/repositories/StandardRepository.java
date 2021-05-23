package de.iplytics.codingchallenge_backend_webapp.api.v1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;

@Repository
public interface StandardRepository extends CrudRepository<Standard, String> {}
