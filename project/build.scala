import sbt._
import Keys._

object ApplicationBuild extends Build {

  val appName        = "mongo-app"
  val apVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "org.reactivemongo" %% "reactivemongo" % "0.10.0" withsources()
  )
  resolvers += "Typesafe repository releases" at
    "http://repo.typesafe.com/typesafe/releases/"
  }
