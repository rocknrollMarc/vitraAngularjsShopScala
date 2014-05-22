package models

import org.joda.time.DateTime
import reactivemongo.bson._
import reactivemongo.bson.handlers._
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentReader
import reactivemongo.bson.BSONDocumentWriter
import reactivemongo.bson.BSONObjectID


case class Product(
  id: Option[BSONObjectID],
  sku: String,
  name: String,
  description: String,
  price: Double,
  cal: Int,
  nutrients: String
)

object Product {
  implicit object ProductBSONReader extends BSONReader[Product] {
    def fromBSON(document: BSONDocument) :Product = {
      val doc = document.toTraversable
      Product(
        doc.getAs[BSONObjectID]("_id"),
        doc.getAs[BSONString]("sku").get.value,
        doc.getAs[BSONString]("name").get.value,
        doc.getAs[BSONString]("description").get.value,
        doc.getAs[BSONDouble]("price").getValue,
        doc.getAs[BSONInt]("cal").getValue,
        doc.getAs[BSONString]("nutrients").getValue
      )
    }
  }

  implicit object ArticleBSONWriter extends BSONWriter[Product] {
    def toBSON(product: Product) = {
      val bson = BSONDocument(
        "_id" -> product.id.getOrElse(BSONObjectID.generate),
        "sku" -> product.BSONString(product.sku),
        "name" -> product.BSONString(product.name),
        "descrition" -> product.BSONString(product.description),
        "price" -> product.BSONDouble(product.price)
      )
    }
  }

  val form = Form(
    mapping(
      "id" -> optional(of[String] verifying pattern(
        """[a-fA-F0-9]{24}""".r,
        "constraint.objectId",
        "error.objectId")),
      "sku" -> nonEmptyText,
      "name" -> text,
      "description" -> nonEmptyText,
      "price" -> optional(of[Double])
    ) { (id, sku, name, description, price) =>
      Product(
        id.map(new BSONObjectID(_)),
        sku,
        name,
        description,
        price
      )} {product =>
    Some(
      (product.id.map(_.stringify),
        product.sku,
        product.name,
        product.description,
        product.price
      )))
    }
  }
