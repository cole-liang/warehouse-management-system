package edu.cqun.warehouse.entity;

import javax.persistence.*;

/**
 * Created by hp on 2015/4/28.
 */
@Entity
@Table(name = "instore_details", schema = "", catalog = "warehouse")
@IdClass(InstoreDetailsEntityPK.class)
public class InstoreDetailsEntity {
    private Integer instoreSheetId;
    private Integer amount;
    private String remark;
    private Integer productId;

    @Id
    @Column(name = "Instore_Sheet_ID")
    public Integer getInstoreSheetId() {
        return instoreSheetId;
    }

    public void setInstoreSheetId(Integer instoreSheetId) {
        this.instoreSheetId = instoreSheetId;
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
    @Column(name = "Amount")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

        InstoreDetailsEntity that = (InstoreDetailsEntity) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (instoreSheetId != null ? !instoreSheetId.equals(that.instoreSheetId) : that.instoreSheetId != null)
            return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instoreSheetId != null ? instoreSheetId.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InStoreDetails:ISheetID:"+instoreSheetId+"\n"+"productId:"+productId+"\n"+
                "amount:"+amount+"\n"+"remark:"+remark;
    }
}
