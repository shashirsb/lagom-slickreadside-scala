organization in ThisBuild := "com.codingkapoor"
version in ThisBuild := "1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.12.8"

resolvers += "OAM 11g" at "https://maven.oracle.com",
credentials += Credentials("OAM 11g", "login.oracle.com", "shashi.rsb@hotmail.com", "Hitmewell123"),
libraryDependencies += "com.oracle.jdbc" % "ojdbc8" % "18.3.0.0" exclude("com.oracle.jdbc", "ucp"),


val mysql = "mysql" % "mysql-connector-java" % "8.0.17"
val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val playJsonDerivedCodecs = "org.julienrf" %% "play-json-derived-codecs" % "4.0.0"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

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
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslPersistenceJdbc,
      lagomScaladslTestKit,
      mysql,
      javaJdbc,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`employee-api`)

lagomCassandraCleanOnStart in ThisBuild := true


