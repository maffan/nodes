/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Marcus
 */
@Entity
@Table(name = "nodetypes")
@NamedQueries({
    @NamedQuery(name = "Nodetype.findAll", query = "SELECT n FROM Nodetype n")})
public class Nodetype implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NodetypePK nodetypePK;
    @Size(max = 50)
    @Column(name = "singular")
    private String singular;
    @Size(max = 50)
    @Column(name = "plural")
    private String plural;
    @JoinColumns({
        @JoinColumn(name = "node", referencedColumnName = "name", insertable = false, updatable = false),
        @JoinColumn(name = "owner", referencedColumnName = "owner", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Node node1;

    public Nodetype() {
    }

    public Nodetype(NodetypePK nodetypePK) {
        this.nodetypePK = nodetypePK;
    }

    public Nodetype(String node, String owner) {
        this.nodetypePK = new NodetypePK(node, owner);
    }

    public NodetypePK getNodetypePK() {
        return nodetypePK;
    }

    public void setNodetypePK(NodetypePK nodetypePK) {
        this.nodetypePK = nodetypePK;
    }

    public String getSingular() {
        return singular;
    }

    public void setSingular(String singular) {
        this.singular = singular;
    }

    public String getPlural() {
        return plural;
    }

    public void setPlural(String plural) {
        this.plural = plural;
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
        hash += (nodetypePK != null ? nodetypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nodetype)) {
            return false;
        }
        Nodetype other = (Nodetype) object;
        if ((this.nodetypePK == null && other.nodetypePK != null) || (this.nodetypePK != null && !this.nodetypePK.equals(other.nodetypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.nomorebagels.entities.Nodetype[ nodetypePK=" + nodetypePK + " ]";
    }
    
}
