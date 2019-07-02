package edu.cqun.warehouse.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by hp on 2015/4/28.
 */
public class StoreAmountEntityPK implements Serializable {
    private Integer monthAt;
    private Integer productId;

    @Column(name = "Month_At")
    @Id
    public Integer getMonthAt() {
        return monthAt;
    }

    public void setMonthAt(Integer monthAt) {
        this.monthAt = monthAt;
    }

    @Column(name = "Product_ID")
    @Id
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreAmountEntityPK that = (StoreAmountEntityPK) o;

        if (monthAt != null ? !monthAt.equals(that.monthAt) : that.monthAt != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = monthAt != null ? monthAt.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        return result;
    }
}
