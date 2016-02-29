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
 * @author flycktm
 */
@Entity
@Table(name = "publicnode")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publicnod.findAll", query = "SELECT p FROM Publicnod p")})
public class Publicnod implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PublicnodPK publicnodPK;
    @JoinColumns({
        @JoinColumn(name = "node", referencedColumnName = "name", insertable = false, updatable = false),
        @JoinColumn(name = "owner", referencedColumnName = "owner", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Node node1;

    public Publicnod() {
    }

    public Publicnod(PublicnodPK publicnodPK) {
        this.publicnodPK = publicnodPK;
    }

    public Publicnod(String node, String owner) {
        this.publicnodPK = new PublicnodPK(node, owner);
    }

    public PublicnodPK getPublicnodPK() {
        return publicnodPK;
    }

    public void setPublicnodPK(PublicnodPK publicnodPK) {
        this.publicnodPK = publicnodPK;
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
        hash += (publicnodPK != null ? publicnodPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publicnod)) {
            return false;
        }
        Publicnod other = (Publicnod) object;
        if ((this.publicnodPK == null && other.publicnodPK != null) || (this.publicnodPK != null && !this.publicnodPK.equals(other.publicnodPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Publicnod[ publicnodPK=" + publicnodPK + " ]";
    }
    
}
