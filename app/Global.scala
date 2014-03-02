import controllers.GlobalConfig
import models.TaskService
import play.api.Application
import play.Logger

object Global extends play.api.GlobalSettings {

  val globalConfig = GlobalConfig(new TaskService())

  // Reader[TaskService, Action]
  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    Logger.info("********** getting controller instance: " + controllerClass)
    // TODO check that controller has appropriate constructor
    controllerClass.getConstructor(classOf[GlobalConfig]).newInstance(globalConfig)
  }
}
