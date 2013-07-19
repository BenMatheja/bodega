package org.relational.repositories;

import org.relational.model.Language;
import org.springframework.data.repository.Repository;

public interface LanguageRepository extends Repository<Language, Long> {
	Language save(Language language);

}
