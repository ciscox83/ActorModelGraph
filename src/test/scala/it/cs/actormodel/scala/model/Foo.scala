package it.cs.actormodel.scala.model

import it.cs.actormodel.java.ActorModel

@ActorModel(parent = classOf[FooParent], children = Array(classOf[FooChild1], classOf[FooChild2]))
class Foo {

}
