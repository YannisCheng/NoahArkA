package com.cwj.anno.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.cwj.common.anno_ref_apt.apt.sample
 *
 * @author ChengWenjia  cwj1714@163.com
 * 2021-12-28 15:23
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface HelloAnno {
}
