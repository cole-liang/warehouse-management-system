package edu.cqun.warehouse.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by hp on 2015/4/28.
 */
public class OutstoreDetailsEntityPK implements Serializable {
    private Integer outstoreSheetId;
    private Integer productId;

    @Column(name = "Outstore_Sheet_ID")
    @Id
    public Integer getOutstoreSheetId() {
        return outstoreSheetId;
    }

    public void setOutstoreSheetId(Integer outstoreSheetId) {
        this.outstoreSheetId = outstoreSheetId;
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

        OutstoreDetailsEntityPK that = (OutstoreDetailsEntityPK) o;

        if (outstoreSheetId != null ? !outstoreSheetId.equals(that.outstoreSheetId) : that.outstoreSheetId != null)
            return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = outstoreSheetId != null ? outstoreSheetId.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        return result;
    }
}
