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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author flycktm
 */
@Entity
@Table(name = "metacollections")
@XmlRootElement
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
    private Collection collection;
    @JoinColumn(name = "child", referencedColumnName = "id")
    @ManyToOne
    private Collection child;

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

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Collection getChild() {
        return child;
    }

    public void setChild(Collection child) {
        this.child = child;
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
        return "entities.Metacollection[ parent=" + parent + " ]";
    }
    
}
