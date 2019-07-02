package edu.cqun.warehouse.entity;

import javax.persistence.*;

/**
 * Created by hp on 2015/4/28.
 */
@Entity
@Table(name = "outstore_details", schema = "", catalog = "warehouse")
@IdClass(OutstoreDetailsEntityPK.class)
public class OutstoreDetailsEntity {
    private Integer outstoreSheetId;
    private Integer productId;
    private Integer amount;
    private String remark;

    @Id
    @Column(name = "Outstore_Sheet_ID")
    public Integer getOutstoreSheetId() {
        return outstoreSheetId;
    }

    public void setOutstoreSheetId(Integer outstoreSheetId) {
        this.outstoreSheetId = outstoreSheetId;
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

        OutstoreDetailsEntity that = (OutstoreDetailsEntity) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (outstoreSheetId != null ? !outstoreSheetId.equals(that.outstoreSheetId) : that.outstoreSheetId != null)
            return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = outstoreSheetId != null ? outstoreSheetId.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OutStoreDetailsEntity: outstoreSheetId:"+outstoreSheetId+"\n"+
                "productId:"+productId+"\n"+"amount:"+amount+"\n"+"remark:"+remark;
    }
}
