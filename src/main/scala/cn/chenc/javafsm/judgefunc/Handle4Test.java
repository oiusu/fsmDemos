package cn.chenc.javafsm.judgefunc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/**
 * Created by ChenC on 2016/9/6.
 */
public class Handle4Test {

//    public boolean isFirst(ContextData context) {
//        return false;
//    }
//
//    public boolean isBusinessLine(ContextData context) {
//        return false;
//    }
//
//    public boolean isDistrict(ContextData context) {
//        return false;
//    }


    public void handle() {
        Collection<Function<ContextData, Boolean>> conditions = new ArrayList<>();
        conditions.add(haha -> isValid(haha));

//        conditions.add(context -> isFirst(context));
//        conditions.add(context -> isBusinessLine(context));
//        conditions.add(context -> isDistrict(context));
    }
    public boolean judge(ContextData context, Collection<Function<ContextData, Boolean>> conditions) {
        for (Function<ContextData, Boolean> cond : conditions) {
            if (!cond.apply(context)) {
                return false;
            }
        }
        return true;
    }

    private static Boolean isValid(ContextData haha){
        return haha.getDistrict_();
    }
}






//class isValid implements  Function{
//
//    @Override
//    public Object apply(Object o) {
//
//        return false;
//    }
//}