val springBootVersion = "1.2.5.RELEASE"
val springBoot = "org.springframework.boot" % "spring-boot-starter" % springBootVersion
val springBootWeb = "org.springframework.boot" % "spring-boot-starter-web" % springBootVersion
val springBootParent = "org.springframework.boot" % "spring-boot-parent" % springBootVersion

lazy val commonSettings = Seq(
  organization := "jp.ijufumi.demo",
  version := "0.1.0",
  scalaVersion := "2.11.7"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "SpringBootOnScala",
    libraryDependencies ++= Seq(
      springBoot, springBootWeb
    )
  )