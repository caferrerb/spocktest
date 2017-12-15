package co.com.velocitypartners.spock.extensions.driver

import groovy.transform.InheritConstructors
import org.openqa.selenium.chrome.ChromeDriver
import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.extension.AbstractMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation
import org.spockframework.runtime.model.FieldInfo
import org.spockframework.runtime.model.SpecInfo
import spock.lang.Specification

class DriverExtension extends AbstractAnnotationDrivenExtension<Driver> {

    @Override
    void visitFieldAnnotation(Driver annotation, FieldInfo field) {
            def interceptor

            interceptor = new DriverInterceptor( field.name,annotation)

            interceptor.install(field.parent.getTopSpec())
    }
}



@InheritConstructors
class DriverInterceptor extends AbstractMethodInterceptor{

    protected final String fieldName
    protected final Driver annotation

    DriverInterceptor() {
    }

    DriverInterceptor(String fieldName,Driver annotation) {
        this.fieldName = fieldName
        this.annotation=annotation
    }

    @Override
    void interceptSetupMethod(IMethodInvocation invocation) {
        def specInstance=getSpec(invocation)
        specInstance."$fieldName" = new ChromeDriver()
        invocation.proceed()
    }

    @Override
    void interceptCleanupMethod(IMethodInvocation invocation) {
        try {
            invocation.proceed()
        } finally {

        }
    }

    @Override
    void install(SpecInfo spec) {
        spec.setupMethod.addInterceptor this
        spec.cleanupMethod.addInterceptor this
    }

    protected final Specification getSpec( IMethodInvocation invocation )
    {
        invocation.instance?:invocation.sharedInstance
    }

}


