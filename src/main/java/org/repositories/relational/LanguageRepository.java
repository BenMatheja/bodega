package org.repositories.relational;

import org.model.relational.Language;
import org.springframework.data.repository.Repository;

public interface LanguageRepository extends Repository<Language, Long> {
	Language save(Language language);

}
