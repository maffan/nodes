/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Marcus
 */
@Embeddable
public class PublicnodePK implements Serializable {

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

    public PublicnodePK() {
    }

    public PublicnodePK(String node, String owner) {
        this.node = node;
        this.owner = owner;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (node != null ? node.hashCode() : 0);
        hash += (owner != null ? owner.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublicnodePK)) {
            return false;
        }
        PublicnodePK other = (PublicnodePK) object;
        if ((this.node == null && other.node != null) || (this.node != null && !this.node.equals(other.node))) {
            return false;
        }
        if ((this.owner == null && other.owner != null) || (this.owner != null && !this.owner.equals(other.owner))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.nomorebagels.entities.PublicnodePK[ node=" + node + ", owner=" + owner + " ]";
    }
    
}
