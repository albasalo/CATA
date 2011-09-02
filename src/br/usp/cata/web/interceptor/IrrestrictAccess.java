package br.usp.cata.web.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public abstract @interface IrrestrictAccess {
}

