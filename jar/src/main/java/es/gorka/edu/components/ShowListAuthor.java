package es.gorka.edu.components;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import es.gorka.edu.model.Author;
import es.gorka.edu.service.AddingAuthorService;

public class ShowListAuthor extends WebPage{
	
	private static final long serialVersionUID = -1935854748907274886L;

	@SpringBean
	AddingAuthorService aService;

	private static final Logger logger = LogManager.getLogger(ShowListAuthor.class.getName());

	private String currentNameSearch = null;

	private List listAuthor = Collections.emptyList();

	public ShowListAuthor(PageParameters parameters) {
		currentNameSearch = parameters.get("currentSearchTerm").toString();
		logger.debug("Cargando la pagina con el parametro " + currentNameSearch);
		initComponents();
	}

	public ShowListAuthor() {
		initComponents();
	}

	private void initComponents() {
		addForm();
		addFeedBackPanel();
		addListAuthorView();
	}

	private void addForm() {
		Form form = new Form("formListingAuthor", new CompoundPropertyModel(new Author())) {
			@Override
			protected void onSubmit() {
				super.onSubmit();
				listAuthor.clear();
				PageParameters pageParameters = new PageParameters();
				pageParameters.add("currentSearchTerm", ((Author) getModelObject()).getNameOfAuthor());
				setResponsePage(ShowListAuthor.class, pageParameters);
			}
		};
		form.add(new TextField("nameOfAuthor"));
		add(form);
	}

	private void addFeedBackPanel() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		add(feedbackPanel);
	}

	private void addListAuthorView() {
		Author author = new Author();// service.newEntity()
		author.setNameOfAuthor(currentNameSearch); 
		listAuthor = aService.listAllAuthors(author);
		ListView listview = new ListView("author-group", listAuthor) {
			@Override
			protected void populateItem(ListItem item) {
				Author author = (Author) item.getModelObject();
				item.add(new Label("authorName", author.getNameOfAuthor()));
				item.add(new Label("dateOfBirth", author.getDateOfBirthFromAuthor()));
			}
		};
		add(listview);
	}

}
