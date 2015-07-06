package it.ciscosistem.actormodel

import java.io.{BufferedWriter, File, FileWriter}
import java.net.URLClassLoader

import com.typesafe.scalalogging.slf4j.Logger
import org.clapper.classutil.ClassFinder
import org.slf4j.LoggerFactory

object ActorModelGenerator {
  val logger = Logger(LoggerFactory.getLogger(ActorModelGenerator.getClass.getSimpleName))

  def generate(inputPackage: String, outputFilePath: String, runtimeClassLoader: URLClassLoader): File = {
    logger.info(s"[ActorModelGenerator] Start processing. Output file: ${outputFilePath}, Input package: ${inputPackage} ...")
    // Start output file
    val outputFile = new File(outputFilePath)
    val bw = new BufferedWriter(new FileWriter(outputFile))
    bw.write("@startuml\n")

    logger.info(s"[ActorModelGenerator] Loading classes from the classpath...")
    val classpath = List(".").map(new File(_))
    val finder = ClassFinder(classpath)
    val classes = try {
      finder.getClasses()
    } catch {
      case e: Exception =>
        logger.error("[ActorModelGenerator] Problem loading classes from classpath", e)
        Stream()
    }

    logger.info(s"[ActorModelGenerator] Loaded ${classes.size} from classpath")

    logger.info(s"[ActorModelGenerator] Building the Actor Model set...")
    val actorModels = classes.filter {
      case c => c.name.contains(inputPackage)
    }.map {
      case c =>
        val clazz = try {
          runtimeClassLoader.loadClass(c.name)
        } catch {
          case e: Exception =>
            logger.error(s"[ActorModelGenerator] Problem getting class for name: ${c.name}. Stop the game.", e)
            throw e
        }
        getActorModelSet(clazz)
    }.toSet.flatten
    logger.info(s"[ActorModelGenerator] Actor Model loaded.")

    logger.info(s"[ActorModelGenerator] Populating the output file...")
    actorModels.foreach {
      case (key: String, value: String) =>
        bw.write(s"[${key}] --> [${value}]" + "\n")
    }
    logger.info(s"[ActorModelGenerator] Output file populated.")


    // End output file
    bw.write("@enduml")
    bw.close()
    logger.info(s"Processing completed.")
    outputFile
  }

  private def getActorModelSet(cls: Class[_]): Set[(String, String)] = {
    val annotation = cls.getAnnotation(classOf[ActorModel])
    if (annotation != null) {

      // include parent relationship
      val parent = annotation.parent()
      val parentSet: Set[(String, String)] = {
        if (parent.getClass.isInstance(classOf[Empty])) Set()
        else Set() + ((parent.getSimpleName, cls.getSimpleName))
      }

      // include children relations
      val childrenSet = annotation.children().filter {
        case child =>
          // TODO there may be a better way on doing this, but using isInstance does not work
          !child.getSimpleName.equals(classOf[Empty].getSimpleName)
      }.map {
        case child =>
          (cls.getSimpleName, child.getSimpleName)
      }.toSet

      // return the union
      parentSet | childrenSet
    } else Set()
  }
}
