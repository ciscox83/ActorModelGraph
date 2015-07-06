package it.ciscosistem.actormodel.scalaExample

import it.ciscosistem.actormodel.ActorModel

@ActorModel(children = Array(classOf[Foo]))
class FooParent {

}
