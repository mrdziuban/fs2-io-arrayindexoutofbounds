def proj(fs2Version: String) = {
  val majorVersion = fs2Version.split('.').head
  Project(s"fs2-$majorVersion", file(s"fs2-$majorVersion")).settings(
    name := s"fs2-$majorVersion-outputstream",
    organization := "com.example",
    scalaVersion := "2.13.6",
    version      := "0.1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      "co.fs2" %% "fs2-core" % fs2Version,
      "co.fs2" %% "fs2-io" % fs2Version
    ),
    run / fork := true
  )
}

lazy val fs2_2 = proj("2.5.9")
lazy val fs2_3 = proj("3.1.3")
