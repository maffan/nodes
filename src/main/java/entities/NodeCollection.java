/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Marcus
 */
@Entity
@Table(name = "collections")
@NamedQueries({
    @NamedQuery(name = "NodeCollection.findAll", query = "SELECT n FROM NodeCollection n")})
public class NodeCollection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "collectionnodes", joinColumns = {
        @JoinColumn(name = "collection", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "node", referencedColumnName = "name"),
        @JoinColumn(name = "owner", referencedColumnName = "owner")})
    @ManyToMany
    private List<Node> nodeList;
    @JoinColumn(name = "owner", referencedColumnName = "mail")
    @ManyToOne
    private User user;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "nodeCollection")
    private Metacollection metacollection;
    @OneToMany(mappedBy = "nodeCollection1")
    private List<Metacollection> metacollectionList;

    public NodeCollection() {
    }

    public NodeCollection(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Metacollection getMetacollection() {
        return metacollection;
    }

    public void setMetacollection(Metacollection metacollection) {
        this.metacollection = metacollection;
    }

    public List<Metacollection> getMetacollectionList() {
        return metacollectionList;
    }

    public void setMetacollectionList(List<Metacollection> metacollectionList) {
        this.metacollectionList = metacollectionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NodeCollection)) {
            return false;
        }
        NodeCollection other = (NodeCollection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.nomorebagels.entities.NodeCollection[ id=" + id + " ]";
    }
    
}
