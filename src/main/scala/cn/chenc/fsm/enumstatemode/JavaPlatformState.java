package cn.chenc.fsm.enumstatemode;

/**
 * Created by ChenC on 2016/9/6.
 * 枚举类 实现抽象方法
 */
public enum JavaPlatformState {
    //  定义state
    OPEN{
        @Override void exit(JavaPlatformMachine pm){super.exit(pm);}
        @Override void valid(JavaPlatformMachine pm){
            this.exit(pm);
            if(pm.data.getValid_()){
                pm.state =STEP1;
            }else{
                NotFound();
                pm.state =OFF;
            }
            pm.state.entry(pm);
        }

        @Override
        void first(JavaPlatformMachine pm) {}

        @Override
        void businessLine(JavaPlatformMachine pm) {}

        @Override
        void district(JavaPlatformMachine pm) {}
    },

    OFF{
        @Override void exit(JavaPlatformMachine pm){super.exit(pm);}

        @Override
        void valid(JavaPlatformMachine pm) {}

        @Override
        void first(JavaPlatformMachine pm) {}

        @Override
        void businessLine(JavaPlatformMachine pm) {}

        @Override
        void district(JavaPlatformMachine pm) {}
    },
    STEP1{
        @Override void exit(JavaPlatformMachine pm){
            super.exit(pm);
        }

        @Override
        void valid(JavaPlatformMachine pm) {}

        @Override void first(JavaPlatformMachine pm){
            this.exit(pm);
            if(!pm.data.getFirst_()){
                pm.state =STEP2;
            }else{
                ReturnDimension();
                pm.state =OFF;
            }
            pm.state.entry(pm);
        }

        @Override
        void businessLine(JavaPlatformMachine pm) {}

        @Override
        void district(JavaPlatformMachine pm) {}
    },

    STEP2{
        @Override void exit(JavaPlatformMachine pm){
            super.exit(pm);
        }

        @Override
        void valid(JavaPlatformMachine pm) {}

        @Override
        void first(JavaPlatformMachine pm) {}

        @Override void businessLine(JavaPlatformMachine pm){
            this.exit(pm);
            if(pm.data.getBusinessLine_()){
                pm.state =STEP3;
            }else{
                PreciseAdvertising();
                pm.state =OFF;
            }
            pm.state.entry(pm);
        }

        @Override
        void district(JavaPlatformMachine pm) {}
    },

    STEP3{
        @Override void exit(JavaPlatformMachine pm){
            super.exit(pm);
        }

        @Override
        void valid(JavaPlatformMachine pm) {}

        @Override
        void first(JavaPlatformMachine pm) {}

        @Override
        void businessLine(JavaPlatformMachine pm) {}

        @Override void district(JavaPlatformMachine pm){
            this.exit(pm);
            if(pm.data.getDistrict_()){
                Top9();
                pm.state =OFF;
            }else{
                pm.state =OFF;
            }
            pm.state.entry(pm);
        }
    },

  ;
    //状态模式 提取的接口  在常量实体类中实现抽象方法
    abstract void valid(JavaPlatformMachine pm);
    abstract void first(JavaPlatformMachine pm);
    abstract void businessLine(JavaPlatformMachine pm);
    abstract void district(JavaPlatformMachine pm);

    //状态机的各种动作action methode
    void entry(JavaPlatformMachine pm){System.out.println("→"+pm.state.name());}
    void exit(JavaPlatformMachine pm){System.out.println(pm.state.name()+"→ ");}

//    void ShutDown(){System.out.println("ShutDown");}
    void NotFound(){System.out.println("NotFound");}
    void ReturnDimension(){System.out.println("ReturnDimension");}
    void PreciseAdvertising(){System.out.println("PreciseAdvertising");}
    void Top9(){System.out.println("Top9");}



}
