package org.repositories;

import org.model.Language;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface LanguageRepository extends GraphRepository <Language> {

}
