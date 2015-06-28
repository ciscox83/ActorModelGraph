package my.place.scala.model

import my.place.java.ActorModel

@ActorModel(parent = classOf[FooParent], children = Array(classOf[FooChild1], classOf[FooChild2]))
class Foo {

}
