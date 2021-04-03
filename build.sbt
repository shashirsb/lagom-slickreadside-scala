organization in ThisBuild := "com.codingkapoor"
version in ThisBuild := "1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.12.8"


val mysql = "mysql" % "mysql-connector-java" % "8.0.17"
val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val playJsonDerivedCodecs = "org.julienrf" %% "play-json-derived-codecs" % "4.0.0"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test



  val TypeSafeConf = "com.typesafe" % "config" % "1.3.0"
  val Mockito = "org.mockito" % "mockito-all" % "1.10.19" % Test
  val FilterHelper = "com.typesafe.play" %% "filters-helpers" % "2.6.12"
  val MySqlConnector = "mysql" % "mysql-connector-java" % "8.0.15"
  val ScalaParser = "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"
  val NettyHandler = "io.netty" % "netty-handler" % "4.1.25.Final"
  val AkkaStream = "com.typesafe.akka" %% "akka-stream" % "2.5.14"
  val AkkaActor = "com.typesafe.akka" %% "akka-actor" % "2.5.14"
  val Guava = "com.google.guava" % "guava" % "22.0"


lazy val `lagom-slickreadside-scala` = (project in file("."))
  .aggregate(`employee-api`, `employee-impl`)

lazy val `employee-api` = (project in file("employee-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi,
      playJsonDerivedCodecs
    )
  )

lazy val `employee-impl` = (project in file("employee-impl"))
  .enablePlugins(LagomScala)
  .settings(resolvers += "OAM 11g" at "https://maven.oracle.com",
    credentials += Credentials("OAM 11g", "login.oracle.com/mysso/signon.jsp", "shashi.rsb@hotmail.com", "Hitmewell123"),
    libraryDependencies += "com.oracle.ojdbc" % "ojdbc8" % "19.3.0.0"
    )
  .settings(    
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceJdbc,
      lagomScaladslTestKit,
      mysql,
      javaJdbc,
      macwire,
      scalaTest,
      Mockito,
      TypeSafeConf
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`employee-api`)


lagomCassandraEnabled in ThisBuild := false
lagomCassandraCleanOnStart in ThisBuild := false


