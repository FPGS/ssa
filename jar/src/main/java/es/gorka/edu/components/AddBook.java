package es.gorka.edu.components;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import es.gorka.edu.dto.BookDTO;
import es.gorka.edu.service.BookService;

public class AddBook extends WebPage{
	@SpringBean
	BookService bService;
	
	@SpringBean
	BookDTO bookDTO;
	
	public AddBook(){
		
		Form <BookDTO> formBook = new Form <BookDTO> ("addingNewBook", new CompoundPropertyModel<BookDTO>(bookDTO)){
			
			private static final long serialVersionUID = 1L;
			
		@Override
		protected void onSubmit(){
			super.onSubmit();
			boolean isInserted = bService.insert(getModelObject());
			FeedbackMessage message;
			if(isInserted){
				message = new FeedbackMessage(this, "SUCCESFULL INSERTION", FeedbackMessage.INFO);
			}else {
				message = new FeedbackMessage(this, "ERROR IN THE INSERTION", FeedbackMessage.INFO);
			}
			getFeedbackMessages().add(message);
		}
		
		};
		formBook.add(new Label("nameBookLabel", getString("book.name")));
		formBook.add(new RequiredTextField("titleOfBook"));
		
		formBook.add(new Label("ISBNLabel", getString("isbn.identifier")));
		formBook.add(new RequiredTextField("isbn"));
		
		formBook.add(new Label("nameAuthorLabel", getString("author.name")));
		formBook.add(new RequiredTextField("nameOfAuthor"));
		
		add(new BookmarkablePageLink<String>("linkReturn", HomePage.class));
		
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		feedbackPanel.setOutputMarkupId(true);
		add(feedbackPanel);

		add(formBook);
	}
}
