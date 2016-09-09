package cn.chenc.fsm.otherdemo.javafsmdemos.judgefunc;

import cn.chenc.fsm.bean.ContextData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/**
 * Created by ChenC on 2016/9/6.
 *
 * 把每一个条件判断都写成一个布尔函数，把这些函数依次放进一个数组/列表。再写一个函数来遍历数组，对每一项，若为假则返回，若为真则继续
 */
public class Handle4Test {


    public static boolean isValid(ContextData data){
        System.out.println("data.getValid_():"+data.getValid_());
        return data.getValid_();
    }

    public static boolean isFirst(ContextData data) {
        System.out.println("data.getFirst_():"+data.getFirst_());
        return data.getFirst_();
    }

    public static boolean isBusinessLine(ContextData data) {
        System.out.println("data.getBusinessLine_():"+data.getBusinessLine_());
        return data.getBusinessLine_();
    }

    public static boolean isDistrict(ContextData data) {
        System.out.println("data.getDistrict_():"+data.getDistrict_());
        return data.getDistrict_();
    }


//    public void handle() {
//        Collection<Function<ContextData, Boolean>> conditions = new ArrayList<>();
//        conditions.add(data -> isValid(data));
//        conditions.add(data -> isFirst(data));
//        conditions.add(data -> isBusinessLine(data));
//        conditions.add(data -> isDistrict(data));
//    }

    public static boolean judge(ContextData data, Collection<Function<ContextData, Boolean>> conditions) {
        for (Function<ContextData, Boolean> cond : conditions) {
            if (!cond.apply(data)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        ContextData contextData = new ContextData(true,true,false,false);

        Collection<Function<ContextData, Boolean>> conditions = new ArrayList<>();
        conditions.add(data -> isValid(data));
        conditions.add(data -> isFirst(data));
        conditions.add(data -> isBusinessLine(data));
        conditions.add(data -> isDistrict(data));
        judge(contextData,conditions);

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