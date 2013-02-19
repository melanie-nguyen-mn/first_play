package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import views.html.*;
import models.Task;

public class Application extends Controller {
	static Form<Task> taskForm = Controller.form(Task.class);
  
  public static Result index() {
    //return ok(index.render("Your new application is ready."));
	 return redirect (routes.Application.tasks());
  }
  
  public static Result hello(String name) {
	  return ok(views.html.hello.render(name));
  }
  
  public static Result TODO() {
	  return ok("Not implemented yet!!!");
  }
  
  public static Result tasks() {
	  return ok(views.html.tasks.render(Task.all(), taskForm));
  }
  
  public static Result newTask() {
	  Form<Task> submitForm = taskForm.bindFromRequest();
	  if (submitForm.hasErrors()) {
		  return badRequest(
				  views.html.tasks.render(Task.all(),  submitForm)
		  );
	  }
	  Task.create(submitForm.get());
	  return redirect(routes.Application.tasks());
  }
  
  public static Result deleteTask(Long id) {
	  Task.delete(id);
	  return redirect(routes.Application.tasks());
  }
  
}