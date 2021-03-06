/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tobedv
 */
@Entity
@Table(name = "collections")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Collection.findAll", query = "SELECT c FROM Collection c"),
    @NamedQuery(name = "Collection.findById", query = "SELECT c FROM Collection c WHERE c.id = :id"),
    @NamedQuery(name = "Collection.findByName", query = "SELECT c FROM Collection c WHERE c.name = :name")})
public class Collection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
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
    @JoinTable(name = "modulecollections", joinColumns = {
        @JoinColumn(name = "collection", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "module", referencedColumnName = "name")})
    @ManyToMany
    private List<Module> moduleList;
    @JoinColumn(name = "owner", referencedColumnName = "mail")
    @ManyToOne
    private User owner;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "collection")
    private Metacollection metacollection;
    @OneToMany(mappedBy = "child")
    private List<Metacollection> metacollectionList;

    public Collection() {
    }

    public Collection(Integer id) {
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

    @XmlTransient
    public List<Node> getNodeList() {
        nodeList.size();
        return nodeList;
    }
    
    public String getNodeListAsJson(){
        List<String> nodeNameList = new ArrayList<>();
        for(Node node : getNodeList()){
            nodeNameList.add(node.getNodePK().getName());
        }
        Gson gson = new Gson();
        return gson.toJson(nodeNameList);
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    @XmlTransient
    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Metacollection getMetacollection() {
        return metacollection;
    }

    public void setMetacollection(Metacollection metacollection) {
        this.metacollection = metacollection;
    }

    @XmlTransient
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
        if (!(object instanceof Collection)) {
            return false;
        }
        Collection other = (Collection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Collection[ id=" + id + " ]";
    }
    
}
