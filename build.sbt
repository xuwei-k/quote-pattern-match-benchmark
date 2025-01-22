scalaVersion := "3.6.3"

enablePlugins(JmhPlugin)

libraryDependencies += "org.scala-lang" %% "scala3-tasty-inspector" % scalaVersion.value
