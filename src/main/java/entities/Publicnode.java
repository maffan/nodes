/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tobedv
 */
@Entity
@Table(name = "publicnode")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publicnode.findAll", query = "SELECT p FROM Publicnode p"),
    @NamedQuery(name = "Publicnode.findByNode", query = "SELECT p FROM Publicnode p WHERE p.publicnodePK.node = :node"),
    @NamedQuery(name = "Publicnode.findByOwner", query = "SELECT p FROM Publicnode p WHERE p.publicnodePK.owner = :owner")})
public class Publicnode implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PublicnodePK publicnodePK;
    @JoinColumns({
        @JoinColumn(name = "node", referencedColumnName = "name", insertable = false, updatable = false),
        @JoinColumn(name = "owner", referencedColumnName = "owner", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Node node1;

    public Publicnode() {
    }

    public Publicnode(PublicnodePK publicnodePK) {
        this.publicnodePK = publicnodePK;
    }

    public Publicnode(String node, String owner) {
        this.publicnodePK = new PublicnodePK(node, owner);
    }

    public PublicnodePK getPublicnodePK() {
        return publicnodePK;
    }

    public void setPublicnodePK(PublicnodePK publicnodePK) {
        this.publicnodePK = publicnodePK;
    }

    public Node getNode1() {
        return node1;
    }

    public void setNode1(Node node1) {
        this.node1 = node1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (publicnodePK != null ? publicnodePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publicnode)) {
            return false;
        }
        Publicnode other = (Publicnode) object;
        if ((this.publicnodePK == null && other.publicnodePK != null) || (this.publicnodePK != null && !this.publicnodePK.equals(other.publicnodePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Publicnode[ publicnodePK=" + publicnodePK + " ]";
    }
    
}
