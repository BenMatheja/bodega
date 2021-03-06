package org.relational.components;

import java.util.ArrayList;
import java.util.Collection;

import org.relational.model.Edge;
import org.relational.model.Language;
import org.relational.model.Model;
import org.relational.model.Vertex;
import org.relational.repositories.EdgeRepository;
import org.relational.repositories.LanguageRepository;
import org.relational.repositories.ModelRepository;
import org.relational.repositories.VertexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * ModelService for RELATIONAL
 * @author Ben Matheja
 *
 */
@Service
public class ModelService {
	@Autowired
	private ModelRepository modelRepository;
	@Autowired
	private LanguageRepository languageRepository;
	@Autowired
	private VertexRepository vertexRepository;
	@Autowired
	private EdgeRepository edgeRepository;

	public Model findModelByTitle(String title) {
		return modelRepository.findByTitle(title);
	}
	
	public Model findModelByEntityId(long id) {
		return modelRepository.findById(id);
	}
	
	public Model findModelByUid(int id) {
		return modelRepository.findByUid(id);
	}

	public Iterable<Model> getAllModels() {
		return modelRepository.findAll();
	}

	public Model createModel(Language lng, String title) {
		Model nm = new Model(title);
		nm.setLanguage(lng);
		return nm;
	}
	
	public void saveModel(Model model) {
		modelRepository.save(model);
	}

	public Language createLanguage(String title, String description) {
		Language l = new Language(title, description);
		return l;
	}
	
	public void saveLanguage(Language language) {
		languageRepository.save(language);
	}

	public Vertex createVertex(String caption) {
		Vertex v = new Vertex(caption);
		vertexRepository.save(v);
		return v;
	}

	public Edge createEdge(Vertex start, Vertex end, String caption) {
		Edge e = new Edge(start, end, caption);
		edgeRepository.save(e);
		return e;
	}
	
	public void addEdge(Edge e, Model model) {
		model.addEdge(e);
		modelRepository.save(model);
	}

	public Collection<Model> makeSomeModels() {
		Collection<Model> models = new ArrayList<Model>();
		// Create Languages first
		Language epk = this.createLanguage("EPK",
				"Ereignisgesteurte Prozesskette");
		Language petri = this.createLanguage("Petri-Net", "Petri-nets for BPM");
		models.add(createModel(epk, "Invoice Managment"));
		models.add(createModel(petri, "Wareneingang"));
		models.add(createModel(epk, "Bestelleingang"));
		models.add(createModel(epk, "Invoice Managment1"));
		models.add(createModel(petri, "Wareneingang1"));
		models.add(createModel(epk, "Bestelleingang1"));
		models.add(createModel(epk, "Invoice Managment2"));
		models.add(createModel(petri, "Wareneingang2"));
		models.add(createModel(epk, "Bestelleingang2"));
		return models;
	}

}
