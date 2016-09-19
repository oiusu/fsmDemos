package cn.chenc.fsm.bean;

/**
 * Created by ChenC on 2016/9/6.
 */
public class ContextData {

    private Boolean isValid_;//广告位是否有效
    private Boolean isFirst_;//是否第一次请求
    private Boolean isBusinessLine_;//是否属于业务线广告位
    private Boolean district_;//是否有地域

    public Boolean getValid_() {
        return isValid_;
    }

    public void setValid_(Boolean valid_) {
        isValid_ = valid_;
    }

    public Boolean getFirst_() {
        return isFirst_;
    }

    public void setFirst_(Boolean first_) {
        isFirst_ = first_;
    }

    public Boolean getBusinessLine_() {
        return isBusinessLine_;
    }

    public void setBusinessLine_(Boolean businessLine_) {
        isBusinessLine_ = businessLine_;
    }

    public Boolean getDistrict_() {
        return district_;
    }

    public void setDistrict_(Boolean district_) {
        this.district_ = district_;
    }

    @Override
    public String toString() {
        return "ContextData{" +
                "isValid_=" + isValid_ +
                ", isFirst_=" + isFirst_ +
                ", isBusinessLine_=" + isBusinessLine_ +
                ", district_=" + district_ +
                '}';
    }

    public ContextData(Boolean isValid_, Boolean isFirst_, Boolean isBusinessLine_,Boolean district_ ) {
        this.isValid_ = isValid_;
        this.isFirst_ = isFirst_;
        this.district_ = district_;
        this.isBusinessLine_ = isBusinessLine_;
    }

    public ContextData() {
    }
}
