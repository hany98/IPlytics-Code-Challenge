package de.iplytics.codingchallenge_backend_webapp.api.v1.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Declaration;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;

@Repository
public interface DeclarationRepository extends CrudRepository<Declaration, Integer> {

	Optional<Declaration> findByPatentAndStandard(Patent patent, Standard standard);

}
