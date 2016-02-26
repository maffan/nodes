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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Marcus
 */
@Entity
@Table(name = "nodes")
@NamedQueries({
    @NamedQuery(name = "Node.findAll", query = "SELECT n FROM Node n")})
public class Node implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NodePK nodePK;
    @ManyToMany(mappedBy = "nodeList")
    private List<NodeCollection> nodeCollectionList;
    @JoinColumn(name = "owner", referencedColumnName = "mail", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "node1")
    private Nodetype nodetype;
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

    public List<NodeCollection> getNodeCollectionList() {
        return nodeCollectionList;
    }

    public void setNodeCollectionList(List<NodeCollection> nodeCollectionList) {
        this.nodeCollectionList = nodeCollectionList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Nodetype getNodetype() {
        return nodetype;
    }

    public void setNodetype(Nodetype nodetype) {
        this.nodetype = nodetype;
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
        return "se.nomorebagels.entities.Node[ nodePK=" + nodePK + " ]";
    }
    
}
