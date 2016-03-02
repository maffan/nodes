/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tobedv
 */
@Entity
@Table(name = "nodes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Node.findAll", query = "SELECT n FROM Node n"),
    @NamedQuery(name = "Node.findByName", query = "SELECT n FROM Node n WHERE n.nodePK.name = :name"),
    @NamedQuery(name = "Node.findByOwner", query = "SELECT n FROM Node n WHERE n.nodePK.owner = :owner")})
public class Node implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NodePK nodePK;
    @ManyToMany(mappedBy = "nodeList")
    private List<Collection> collectionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "node1", orphanRemoval=true)
    private List<Datapoint> datapointList;
    @JoinColumn(name = "owner", referencedColumnName = "mail", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "node1")
    private Publicnode publicnode;

    public Node() {
    }

    public Node(NodePK nodePK) {
        this.nodePK = nodePK;
    }

    public Node(String name, String owner) {
        this.nodePK = new NodePK(name, owner);
    }

    public NodePK getNodePK() {
        return nodePK;
    }

    public void setNodePK(NodePK nodePK) {
        this.nodePK = nodePK;
    }

    @XmlTransient
    public List<Collection> getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(List<Collection> collectionList) {
        this.collectionList = collectionList;
    }

    @XmlTransient
    public List<Datapoint> getDatapointList() {
        return datapointList;
    }

    public void setDatapointList(List<Datapoint> datapointList) {
        this.datapointList = datapointList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Publicnode getPublicnode() {
        return publicnode;
    }

    public void setPublicnode(Publicnode publicnode) {
        this.publicnode = publicnode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nodePK != null ? nodePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Node)) {
            return false;
        }
        Node other = (Node) object;
        if ((this.nodePK == null && other.nodePK != null) || (this.nodePK != null && !this.nodePK.equals(other.nodePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Node[ nodePK=" + nodePK + " ]";
    }
    
}
