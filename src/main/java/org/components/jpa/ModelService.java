package org.components.jpa;

import java.util.ArrayList;
import java.util.Collection;

import org.model.relational.Edge;
import org.model.relational.Language;
import org.model.relational.Model;
import org.model.relational.Vertex;
import org.repositories.relational.EdgeRepository;
import org.repositories.relational.LanguageRepository;
import org.repositories.relational.ModelRepository;
import org.repositories.relational.VertexRepository;
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

	public Model findModelById(Long id) {
		return modelRepository.findById(id);
	}

	public Iterable<Model> getAllModels() {
		return modelRepository.findAll();
	}

	public Model createModel(Language lng, String title) {
		Model nm = new Model(title);
		nm.setLanguage(lng);
		modelRepository.save(nm);
		return nm;
	}

	public Language createLanguage(String title, String description) {
		Language l = new Language(title, description);
		languageRepository.save(l);
		return l;
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
