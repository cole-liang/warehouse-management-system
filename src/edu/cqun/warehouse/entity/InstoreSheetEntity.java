package edu.cqun.warehouse.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by hp on 2015/4/28.
 */
@Entity
@Table(name = "instore_sheet", schema = "", catalog = "warehouse")
public class InstoreSheetEntity {
    private Integer instoreSheetId;
    private Timestamp dateAt;
    private String department;
    private String checker;
    private Timestamp modifyDateAt;
    private String modifier;

    @Id
    @Column(name = "Instore_Sheet_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getInstoreSheetId() {
        return instoreSheetId;
    }

    public void setInstoreSheetId(Integer instoreSheetId) {
        this.instoreSheetId = instoreSheetId;
    }

    @Basic
    @Column(name = "Date_At")
    public Timestamp getDateAt() {
        return dateAt;
    }

    public void setDateAt(Timestamp dateAt) {
        this.dateAt = dateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstoreSheetEntity that = (InstoreSheetEntity) o;

        if (checker != null ? !checker.equals(that.checker) : that.checker != null) return false;
        if (dateAt != null ? !dateAt.equals(that.dateAt) : that.dateAt != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (instoreSheetId != null ? !instoreSheetId.equals(that.instoreSheetId) : that.instoreSheetId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instoreSheetId != null ? instoreSheetId.hashCode() : 0;
        result = 31 * result + (dateAt != null ? dateAt.hashCode() : 0);
        result = 31 * result + (checker != null ? checker.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InStoreSheet: ID:"+instoreSheetId+"\n"+"dataAt:"+dateAt.toString()+"\n"+
                "checker:"+checker+"\n"+"department:"+department+"\n"+
                "modifyDateAt:"+modifyDateAt.toString()+"\n"+"modifier:"+modifier;
    }

    @Basic
    @Column(name = "Department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @Column(name = "Checker")
    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    @Basic
    @Column(name = "Modify_Date_At")
    public Timestamp getModifyDateAt() {
        return modifyDateAt;
    }

    public void setModifyDateAt(Timestamp modifyDateAt) {
        this.modifyDateAt = modifyDateAt;
    }

    @Basic
    @Column(name = "Modifier")
    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
