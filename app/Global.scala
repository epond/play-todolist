import play.api.Application
import play.Logger

object Global extends play.api.GlobalSettings {

  override def onStart(app: Application): Unit = {
  }

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    Logger.info("********** getting controller instance: " + controllerClass)
    controllerClass.newInstance()
  }
}
