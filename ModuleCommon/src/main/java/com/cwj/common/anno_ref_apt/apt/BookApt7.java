package com.cwj.common.anno_ref_apt.apt;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * com.cwj.common.anno_ref_apt.apt
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-27 13:55
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.cwj.common.anno_ref_apt.annotation.BindView")
public class BookApt7 extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return true;
    }
}
