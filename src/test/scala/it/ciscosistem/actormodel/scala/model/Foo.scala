package it.ciscosistem.actormodel.scala.model

import it.ciscosistem.actormodel.java.ActorModel

@ActorModel(parent = classOf[FooParent], children = Array(classOf[FooChild1], classOf[FooChild2]))
class Foo {

}
