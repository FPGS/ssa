package es.gorka.edu.components;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePicker;
import es.gorka.edu.model.Author;

public class AuthorPage extends WebPage{
	
	public AuthorPage() {
		Form form = new Form("addingNewAuthor", new CompoundPropertyModel(new Author())){};
		form.add(new Label("nameAuthorLabel", getString("author.name")));
		form.add(new Label("dateOfBirthLabel", getString("date.of.birth")));
		form.add(new RequiredTextField("nameAuthor"));
		DatetimePicker datetimePicker = new DatetimePicker("dateOfBirth", "yyyy-MM-dd");
		form.add(datetimePicker);
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		feedbackPanel.setOutputMarkupId(true);
		add(feedbackPanel);

		add(form);
	}
}
