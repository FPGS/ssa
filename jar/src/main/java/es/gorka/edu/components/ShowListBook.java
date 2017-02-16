package es.gorka.edu.components;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import es.gorka.edu.model.Book;
import es.gorka.edu.service.AddingBookService;

public class ShowListBook  extends WebPage {
	
	private static final long serialVersionUID = -1935854748907274886L;

	@SpringBean
	AddingBookService bService;

	private static final Logger logger = LogManager.getLogger(ShowListBook.class.getName());
	
	private String titleParameter = null;
	private String isbnParameter = null;
	private String nameAuthorParameter = null;

	private List listBook = Collections.emptyList();

	public ShowListBook(PageParameters parameters) {
		titleParameter = parameters.get("titleParam").toString();
		isbnParameter = parameters.get("isbnParam").toString();
		nameAuthorParameter = parameters.get("nameAuthorParam").toString();
		logger.debug("Cargando la pagina con el parametro Titulo: " + titleParameter + " ISBN: " + isbnParameter + " Autor: " + nameAuthorParameter);
		initComponents();
	}

	public ShowListBook() {
		initComponents();
	}

	private void initComponents() {
		addForm();
		addFeedBackPanel();
		addListBookView();
	}
	
	
	private void addForm() {
		Form form = new Form("formListingBook", new CompoundPropertyModel(new Book())) {
			@Override
			protected void onSubmit() {
				super.onSubmit();
				listBook.clear();
				PageParameters pageParameters = new PageParameters();
				pageParameters.add("currentSearchTerm", ((Book) getModelObject()).getTitleOfBook());
				setResponsePage(ShowListBook.class, pageParameters);
			}
		};
		form.add(new TextField("titleOfBook"));
		form.add(new TextField("isbn"));
		form.add(new TextField("nameOfAuthor"));
		
		add(new BookmarkablePageLink<String>("linkReturn", HomePage.class));
		
		add(form);
	}

	private void addFeedBackPanel() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		add(feedbackPanel);
	}

	private void addListBookView() {
		Book book = new Book();// service.newEntity()
		
		book.setTitleOfBook(titleParameter); 
		
		book.setIsbn(isbnParameter);; 
		
		book.setNameOfAuthor(nameAuthorParameter);; 
		
		listBook = bService.listAllBooks(book);
		ListView listview = new ListView("book-group", listBook) {
			@Override
			protected void populateItem(ListItem item) {
				Book book = (Book) item.getModelObject();
				item.add(new Label("bookTitle", book.getTitleOfBook()));
				item.add(new Label("isbn", book.getIsbn()));
				item.add(new Label("authorName", book.getNameOfAuthor()));	
			}
		};
		add(listview);
	}

}
