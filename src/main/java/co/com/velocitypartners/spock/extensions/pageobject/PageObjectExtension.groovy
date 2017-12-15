package co.com.velocitypartners.spock.extensions.pageobject

import co.com.velocitypartners.spock.extensions.pageurl.PageUrl
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

class PageObjectExtesion extends AbstractAnnotationDrivenExtension<PageObject> {

    @Override
    void visitFieldAnnotation(PageObject annotation, FieldInfo field) {
            def interceptor

            interceptor = new PageObjectInterceptor( field,annotation)

            interceptor.install(field.parent.getTopSpec())
    }
}


@InheritConstructors
class PageObjectInterceptor extends AbstractMethodInterceptor{

    protected final FieldInfo field
    protected final PageObject annotation

    PageObjectInterceptor() {
    }

    PageObjectInterceptor(FieldInfo fieldName, PageObject annotation) {
        this.field = fieldName
        this.annotation=annotation
    }

    @Override
    void interceptSetupMethod(IMethodInvocation invocation) {
        def specInstance=getSpec(invocation)
        co.com.velocitypartners.prueba.util.PageObject po=(field.getType()).newInstance()
        specInstance."$field.name" = po


        if(field.isAnnotationPresent(PageUrl)){
            PageUrl pUrl=field.getAnnotation(PageUrl)
            String url=null;
            if(field.isAnnotationPresent(Parameter)){
                Parameter param=field.getAnnotation(Parameter)
                String file=param.file()
                String key=pUrl.url()

                url= PropertiesUtil.get(file,key)

            }else{

                if(pUrl.harcode()){
                    url=pUrl.url()
                }else{
                    throw new RuntimeException("Url mal configurada, debe usar @Parameter o el hasrCode debe ser true")
                }
            }
            if(annotation.preLoad()){
                po.navegar(url)
            }else{
                po.setUrl(url)
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


