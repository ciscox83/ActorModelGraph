package my.place.scala

import java.net.URLClassLoader

import my.place.scala.ActorModelGeneratorScalaTest.{InputPackage, OutputPath, expected_output}
import org.scalatest.FunSuite

import scala.io.Source.fromFile

class ActorModelGeneratorScalaTest extends FunSuite {

  test("Simple Test") {
    val output_file = ActorModelGenerator.generate(InputPackage, OutputPath, getUrlContextClassLoader)
    val actual_output = fromFile(output_file).mkString.stripMargin
    assert(expected_output.equals(actual_output))
  }

  def getUrlContextClassLoader: URLClassLoader = {
    Thread.currentThread().getContextClassLoader().asInstanceOf[URLClassLoader]
  }
}

object ActorModelGeneratorScalaTest {
  val OutputPath = "target/scala_model.txt"
  val InputPackage = "my.place.scala"

  val expected_output =
    """@startuml
      |[Foo] --> [FooChild1]
      |[Foo] --> [FooChild2]
      |[FooParent] --> [Foo]
      |@enduml""".stripMargin
}
