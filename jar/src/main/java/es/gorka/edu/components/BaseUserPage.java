package es.gorka.edu.components;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;


public class BaseUserPage extends WebPage {

	private FeedbackPanel feedbackPanel;

	public BaseUserPage() {
		super();
	}

	public BaseUserPage(IModel<?> model) {
		super(model);
	}

	public BaseUserPage(PageParameters parameters) {
		super(parameters);
	}

	protected void fillUserForm(Form form) {
		form.add(new Label("nameLabel", getString("username")));
		form.add(new Label("passwordLabel", getString("password")));
		form.add(new RequiredTextField("name"));
		form.add(new RequiredTextField("password"));
		feedbackPanel = new FeedbackPanel("feedbackMessage");
		feedbackPanel.setOutputMarkupId(true);
		add(getFeedbackPanel());
	}

	protected FeedbackPanel getFeedbackPanel() {
		return feedbackPanel;
	}


}