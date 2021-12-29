package com.cwj.anno.apt;

//import com.squareup.javapoet.JavaFile;
//import com.squareup.javapoet.MethodSpec;
//import com.squareup.javapoet.TypeSpec;

import com.cwj.anno.anno.HelloAnno;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * com.cwj.common.anno_ref_apt.apt.sample
 *
 * @author ChengWenjia  cwj1714@163.com
 * 2021-12-28 15:24
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.cwj.common.anno_ref_apt.apt.sample.HelloAnno")
public class HelloProcess extends AbstractProcessor {

    private Elements elementUtils;
    private Filer filer;
    private Messager messager;
    private SourceVersion sourceVersion;
    private Types typeUtils;
    private Locale locale;
    private Map<String, String> options;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
        sourceVersion = processingEnv.getSourceVersion();
        typeUtils = processingEnv.getTypeUtils();
        locale = processingEnv.getLocale();
        options = processingEnv.getOptions();

        System.out.println("Element：" + elementUtils.toString());
        System.out.println("Filer：" + filer.toString());
        System.out.println("Messager: " + messager.toString());
        System.out.println("sourceVersion: " + sourceVersion.toString());
        System.out.println("typeUtils: " + typeUtils.toString());
        System.out.println("locale: " + locale.toString());
        System.out.println("options: " + options.toString());
        messager.printMessage(Diagnostic.Kind.WARNING, "init() «««««««");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager.printMessage(Diagnostic.Kind.WARNING, "process() ««««««");

        for (TypeElement element : annotations) {
            System.out.println("element.getQualifiedName: " + element.getQualifiedName().toString());
            System.out.println("cannonicalName:" + HelloAnno.class.getCanonicalName());
            // 如果当前注解为：HelloAnno
            if (element.getQualifiedName().toString().equals(HelloAnno.class.getCanonicalName())) {
                System.out.println("element info : " + element.toString());
            }
        }
        return true;
    }


    //@Override
    //public SourceVersion getSupportedSourceVersion() {
    //    return SourceVersion.latestSupported();
    //}
    //
    //@Override
    //public Set<String> getSupportedAnnotationTypes() {
    //    return Collections.singleton(HelloAnno.class.getCanonicalName());
    //}
}
