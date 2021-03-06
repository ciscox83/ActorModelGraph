package it.ciscosistem.actormodel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ActorModel {
    Class parent() default Empty.class;
    Class[] children() default Empty.class;
}
