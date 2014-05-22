package controllers

import play.api._
import play.api.mvc._
import play.api.Play.current
import play.modules.reactivemongo._
import  models._

import reactivemongo.api._
import reactivemongo.bson._
import reactivemongo.bson.handlers.DefaultBSONHandlers._

object Application extends Controller with MongoController {

  def index = Action {
    Ok()
  }

  def list = Action { implicit request =>
    Async {
      implicit val reader = Product.ProductBSONReader
      val collection = db.collection("products")
      // empty query to match all documents
      val query = BSONDocument()
      // The future cursor of documents
      val found = collection.find(query)
      // build (asyncronously) a list containing all products
      found.toList.map { products =>
        Ok(views.html.products(products, activeSort))
      }
    }
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
