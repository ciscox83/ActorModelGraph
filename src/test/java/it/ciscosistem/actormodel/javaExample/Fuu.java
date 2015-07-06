package it.ciscosistem.actormodel.javaExample;

import it.ciscosistem.actormodel.ActorModel;

@ActorModel(parent = FuuParent.class, children = {FuuChild1.class, FuuChild2.class})
public class Fuu {
}
