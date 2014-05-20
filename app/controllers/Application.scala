package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def jsRoutes(varName: String = "jsRoutes") = Action { implicit request =>
    Ok(
        Routes.javascriptRouter(varName)(
            routes.javascript.Application.login,
            routes.javascript.Users.user
            )
        ).as(JAVASCRIPT)
  }

}