package edu.cqun.warehouse.entity;

import javax.persistence.*;

/**
 * Created by hp on 2015/4/28.
 */
@Entity
@Table(name = "store_amount", schema = "", catalog = "warehouse")
@IdClass(StoreAmountEntityPK.class)
public class StoreAmountEntity {
    private Integer monthAt;
    private Integer productId;
    private Integer thisMonthAmount;
    private String remark;

    @Id
    @Column(name = "Month_At")
    public Integer getMonthAt() {
        return monthAt;
    }

    public void setMonthAt(Integer monthAt) {
        this.monthAt = monthAt;
    }

    @Id
    @Column(name = "Product_ID")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "This_Month_Amount")
    public Integer getThisMonthAmount() {
        return thisMonthAmount;
    }

    public void setThisMonthAmount(Integer thisMonthAmount) {
        this.thisMonthAmount = thisMonthAmount;
    }

    @Basic
    @Column(name = "Remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreAmountEntity that = (StoreAmountEntity) o;

        if (monthAt != null ? !monthAt.equals(that.monthAt) : that.monthAt != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (thisMonthAmount != null ? !thisMonthAmount.equals(that.thisMonthAmount) : that.thisMonthAmount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = monthAt != null ? monthAt.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (thisMonthAmount != null ? thisMonthAmount.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StoreAmount: monthAt:"+monthAt+"\n"+"productId:"+productId+"\n"+
                "thisMonthAmount:"+thisMonthAmount+"\n"+"remark:"+remark;
    }
}
