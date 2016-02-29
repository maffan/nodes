/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author flycktm
 */
@Entity
@Table(name = "datapoints")
@NamedQueries({
    @NamedQuery(name = "Datapoint.findAll", query = "SELECT d FROM Datapoint d")})
public class Datapoint implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DatapointPK datapointPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "data")
    private String data;

    public Datapoint() {
    }

    public Datapoint(DatapointPK datapointPK) {
        this.datapointPK = datapointPK;
    }

    public Datapoint(DatapointPK datapointPK, String data) {
        this.datapointPK = datapointPK;
        this.data = data;
    }

    public Datapoint(String node, String owner, Date time) {
        this.datapointPK = new DatapointPK(node, owner, time);
    }

    public DatapointPK getDatapointPK() {
        return datapointPK;
    }

    public void setDatapointPK(DatapointPK datapointPK) {
        this.datapointPK = datapointPK;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (datapointPK != null ? datapointPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datapoint)) {
            return false;
        }
        Datapoint other = (Datapoint) object;
        if ((this.datapointPK == null && other.datapointPK != null) || (this.datapointPK != null && !this.datapointPK.equals(other.datapointPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Datapoint[ datapointPK=" + datapointPK + " ]";
    }
    
}
