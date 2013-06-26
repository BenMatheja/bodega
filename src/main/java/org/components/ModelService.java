package org.components;

import org.model.Language;
import org.model.Model;
import org.model.Vertex;
import org.repositories.LanguageRepository;
import org.repositories.ModelRepository;
import org.repositories.VertexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {
@Autowired
private ModelRepository modelRepository;
@Autowired
private LanguageRepository languageRepository;
@Autowired
private VertexRepository vertexRepository;

public Model findModelByTitle(String title){
	return modelRepository.findByPropertyValue("title", title);
	
}
public Model createModel(Language lng, String title){
	return modelRepository.save(new Model(lng,title));
}
public Language createLanguage(String title, String description){
	return languageRepository.save(new Language(title,description));
}
public Vertex createVertex(String caption){
	return vertexRepository.save(new Vertex(caption));
}
}
