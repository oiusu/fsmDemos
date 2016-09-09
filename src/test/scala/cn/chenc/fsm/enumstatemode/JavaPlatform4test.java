package cn.chenc.fsm.enumstatemode;


/**
 * Created by ChenC on 2016/9/6 0006.
 */
public class JavaPlatform4test {


        public static void main(String[] args){

            JavaPlatformMachine pm = new JavaPlatformMachine();
            pm.data.setValid_(true);
            pm.data.setFirst_(false);
            pm.data.setBusinessLine_(true);
            pm.data.setDistrict_(true);

            System.out.println("Current State:" + pm.state.name());
            pm.valid();
            pm.first();
            pm.businessLine();
            pm.district();

        }


}
