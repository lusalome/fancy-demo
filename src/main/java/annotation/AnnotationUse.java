package annotation;

import util.TradeStatus;

/*
 * 把自定义的注解MyAnnotation应用到某个类上
 */
@MyAnnotation(entryStatus = TradeStatus.TO_APPLY)
public class AnnotationUse {

}
