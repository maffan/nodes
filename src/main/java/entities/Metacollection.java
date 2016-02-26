/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Marcus
 */
@Entity
@Table(name = "metacollections")
@NamedQueries({
    @NamedQuery(name = "Metacollection.findAll", query = "SELECT m FROM Metacollection m")})
public class Metacollection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "parent")
    private Integer parent;
    @JoinColumn(name = "parent", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private NodeCollection nodeCollection;
    @JoinColumn(name = "child", referencedColumnName = "id")
    @ManyToOne
    private NodeCollection nodeCollection1;

    public Metacollection() {
    }

    public Metacollection(Integer parent) {
        this.parent = parent;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public NodeCollection getNodeCollection() {
        return nodeCollection;
    }

    public void setNodeCollection(NodeCollection nodeCollection) {
        this.nodeCollection = nodeCollection;
    }

    public NodeCollection getNodeCollection1() {
        return nodeCollection1;
    }

    public void setNodeCollection1(NodeCollection nodeCollection1) {
        this.nodeCollection1 = nodeCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parent != null ? parent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Metacollection)) {
            return false;
        }
        Metacollection other = (Metacollection) object;
        if ((this.parent == null && other.parent != null) || (this.parent != null && !this.parent.equals(other.parent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.nomorebagels.entities.Metacollection[ parent=" + parent + " ]";
    }
    
}
