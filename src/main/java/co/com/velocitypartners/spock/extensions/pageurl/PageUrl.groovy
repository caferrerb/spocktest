package co.com.velocitypartners.spock.extensions.pageurl

import org.spockframework.runtime.extension.ExtensionAnnotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
//@ExtensionAnnotation(PageUrlExtension)
@interface PageUrl {
    String url()
    boolean harcode() default false
}
