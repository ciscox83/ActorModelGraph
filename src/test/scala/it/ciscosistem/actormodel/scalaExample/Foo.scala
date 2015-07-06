package it.ciscosistem.actormodel.scalaExample

import it.ciscosistem.actormodel.ActorModel

@ActorModel(parent = classOf[FooParent], children = Array(classOf[FooChild1], classOf[FooChild2]))
class Foo {

}
