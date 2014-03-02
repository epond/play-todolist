package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

case class Application(globalConfig: GlobalConfig) extends Controller {

  val taskService = globalConfig.taskService

  val taskForm = Form(
    "label" -> nonEmptyText
  )
  
  def index = Action {
    Redirect(routes.Application.tasks)
  }

  def tasks = Action {
    Ok(views.html.index(taskService.all(), taskForm))
  }

  def newTask = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(taskService.all(), errors)),
      label => {
        taskService.create(label)
        Redirect(routes.Application.tasks)
      }
    )
  }

  def deleteTask(id: Long) = Action {
    taskService.delete(id)
    Redirect(routes.Application.tasks)
  }
  
}