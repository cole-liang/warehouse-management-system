package edu.cqun.warehouse.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by hp on 2015/5/2.
 */
public class InstoreDetailsEntityPK implements Serializable {
    private Integer instoreSheetId;
    private Integer productId;

    @Column(name = "Instore_Sheet_ID")
    @Id
    public Integer getInstoreSheetId() {
        return instoreSheetId;
    }

    public void setInstoreSheetId(Integer instoreSheetId) {
        this.instoreSheetId = instoreSheetId;
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

        InstoreDetailsEntityPK that = (InstoreDetailsEntityPK) o;

        if (instoreSheetId != null ? !instoreSheetId.equals(that.instoreSheetId) : that.instoreSheetId != null)
            return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instoreSheetId != null ? instoreSheetId.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        return result;
    }
}
