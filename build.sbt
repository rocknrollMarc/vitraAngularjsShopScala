name := "vitraAngularjsShopScala"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "org.webjars" % "angularjs" % "1.0.7",
  "org.webjars" % "requirejs" % "2.1.1",
  "org.webjars" % "webjars-play" % "2.1.0-1",
  "org.reactivemongo" %% "reactivemongo" % "0.10.0"
)

resolvers += "Typesafe repository releases" at
"http://repo.typesafe.com/typesafe/releases/"

play.Project.playScalaSettings
