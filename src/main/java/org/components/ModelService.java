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
public Model findModelById(Long id){
	return modelRepository.findById(id);
}
public Model createModel(Language lng, String title){
	Model nm = new Model(title);
	nm.setLanguage(lng);
	return modelRepository.save(nm);
	
}
public Language createLanguage(String title, String description){
	Language l = new Language(title,description);
	return languageRepository.save(l);
}
public Vertex createVertex(String caption, Model model){
	Vertex v = new Vertex(caption);
	v.setModel(model);
	return vertexRepository.save(v);
}
}
