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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author flycktm
 */
@Embeddable
public class DatapointPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "node")
    private String node;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "owner")
    private String owner;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public DatapointPK() {
    }

    public DatapointPK(String node, String owner, Date time) {
        this.node = node;
        this.owner = owner;
        this.time = time;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (node != null ? node.hashCode() : 0);
        hash += (owner != null ? owner.hashCode() : 0);
        hash += (time != null ? time.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatapointPK)) {
            return false;
        }
        DatapointPK other = (DatapointPK) object;
        if ((this.node == null && other.node != null) || (this.node != null && !this.node.equals(other.node))) {
            return false;
        }
        if ((this.owner == null && other.owner != null) || (this.owner != null && !this.owner.equals(other.owner))) {
            return false;
        }
        if ((this.time == null && other.time != null) || (this.time != null && !this.time.equals(other.time))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DatapointPK[ node=" + node + ", owner=" + owner + ", time=" + time + " ]";
    }
    
}
