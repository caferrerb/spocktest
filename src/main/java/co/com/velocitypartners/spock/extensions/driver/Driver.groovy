package co.com.velocitypartners.spock.extensions.driver

import org.spockframework.runtime.extension.ExtensionAnnotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ExtensionAnnotation(DriverExtension)
@interface Driver {
    String baseDir() default 'build/test'
    boolean clean() default false
}
