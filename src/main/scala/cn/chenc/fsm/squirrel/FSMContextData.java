package cn.chenc.fsm.squirrel;

/**
 * Created by ChenC on 2016/9/13.
 */
public class FSMContextData {
    private Boolean valid;//广告位是否有效
    private Boolean privateAD;//是否私有广告位
    private Boolean privateAdvertiser;//是否私有广告主
    private Boolean fixedDelivery;//广告位是否有定投广告计划
    private Boolean unFixedPmp;//是否有非定投PMP广告计划
    private Boolean unFixedUnPmp;//是否有非定投非PMP广告计划
    private Boolean noMaterialDelivery;//是否没有素材可投

    public Boolean isValid() {
        return valid;
    }

    public Boolean isPrivateAD() {
        return privateAD;
    }

    public Boolean isPrivateAdvertiser() {
        return privateAdvertiser;
    }

    public Boolean isFixedDelivery() {
        return fixedDelivery;
    }

    public Boolean isUnFixedPmp() {
        return unFixedPmp;
    }

    public Boolean isUnFixedUnPmp() {
        return unFixedUnPmp;
    }

    public Boolean isNoMaterialDelivery() {
        return noMaterialDelivery;
    }

    public FSMContextData(Boolean valid, Boolean privateAD, Boolean privateAdvertiser, Boolean fixedDelivery, Boolean unFixedPmp, Boolean unFixedUnPmp, Boolean noMaterialDelivery) {
        this.valid = valid;
        this.privateAD = privateAD;
        this.privateAdvertiser = privateAdvertiser;
        this.fixedDelivery = fixedDelivery;
        this.unFixedPmp = unFixedPmp;
        this.unFixedUnPmp = unFixedUnPmp;
        this.noMaterialDelivery = noMaterialDelivery;
    }
}
