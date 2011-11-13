import sbt._
import Keys._

object MultiBuild extends Build {

  lazy val multi = Project(id = "root", base = file("."), aggregate = Seq(pythonChallenge))

  lazy val pythonChallenge = Project(id = "python-challenge", base = file("python-challenge"))

  lazy val projectEuler = Project(id = "project-euler", base = file("project-euler"))

  lazy val dependencies = Seq(
    "org.scalatest" %% "scalatest" % "1.6.1" % "test",
    "org.scala-tools.time" %% "time" % "0.5"
  )

  lazy val settingsForAllProjects = Seq(
    libraryDependencies ++= dependencies
  )

  override lazy val settings = super.settings ++ settingsForAllProjects

}

