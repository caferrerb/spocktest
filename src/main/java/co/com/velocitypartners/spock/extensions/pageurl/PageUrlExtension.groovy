package co.com.velocitypartners.spock.extensions.pageurl

import co.com.velocitypartners.spock.extensions.parameter.Parameter
import co.com.velocitypartners.util.PropertiesUtil
import groovy.transform.InheritConstructors
import org.openqa.selenium.chrome.ChromeDriver
import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.extension.AbstractMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation
import org.spockframework.runtime.model.FieldInfo
import org.spockframework.runtime.model.SpecInfo
import spock.lang.Specification

class PageUrlExtension extends AbstractAnnotationDrivenExtension<PageUrl> {

    @Override
    void visitFieldAnnotation(PageUrl annotation, FieldInfo field) {
            def interceptor

            interceptor = new PageUrlInterceptor( field,annotation)

            interceptor.install(field.parent.getTopSpec())
    }
}


@InheritConstructors
class PageUrlInterceptor extends AbstractMethodInterceptor{

    protected final FieldInfo fieldName
    protected final PageUrl annotation

    PageUrlInterceptor() {
    }

    PageUrlInterceptor(FieldInfo fieldName, PageUrl annotation) {
        this.fieldName = fieldName
        this.annotation=annotation
    }

    @Override
    void interceptSetupMethod(IMethodInvocation invocation) {
        def specInstance=getSpec(invocation)
        if(annotation.harcode()) {
            specInstance."$fieldName.name" = annotation.url()
        }else{
            if(fieldName.isAnnotationPresent(Parameter)){
                Parameter param=fieldName.getAnnotation(Parameter)
                String file=param.file()
                String key=annotation.url()

               String valor= PropertiesUtil.get(file,key)
               specInstance."$fieldName.name"=valor
            }
        }
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


