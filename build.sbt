name := "in-class-code"

version := "5.0"

scalaVersion := "3.3.0"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.16"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.16" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.3.0"

libraryDependencies += "org.bitbucket.inkytonik.kiama" % "kiama_3" % "2.5.1"

libraryDependencies +=
  "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4"

Test / parallelExecution := false
