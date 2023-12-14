name := "in-class-code"

version := "5.0"

scalaVersion := "3.3.0"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.16"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.16" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.3.0"

libraryDependencies += "org.bitbucket.inkytonik.kiama" % "kiama_3" % "2.5.1"

libraryDependencies +=
  "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4"

resolvers += "Akka library repository".at("https://repo.akka.io/maven")

val AkkaVersion = "2.9.0"
libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion

libraryDependencies ++= Seq("org.slf4j" % "slf4j-api" % "2.0.9",
                            "org.slf4j" % "slf4j-simple" % "2.0.9")

Test / parallelExecution := false
