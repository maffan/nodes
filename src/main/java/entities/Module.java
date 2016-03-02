/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author flycktm
 */
@Entity
@Table(name = "modules")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Module.findAll", query = "SELECT m FROM Module m"),
    @NamedQuery(name = "Module.findByName", query = "SELECT m FROM Module m WHERE m.name = :name"),
    @NamedQuery(name = "Module.findByJsp", query = "SELECT m FROM Module m WHERE m.jsp = :jsp")})
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 50)
    @Column(name = "jsp")
    private String jsp;
    @ManyToMany(mappedBy = "moduleList")
    private List<Collection> collectionList;

    public Module() {
    }

    public Module(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJsp() {
        return jsp;
    }

    public void setJsp(String jsp) {
        this.jsp = jsp;
    }

    @XmlTransient
    public List<Collection> getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(List<Collection> collectionList) {
        this.collectionList = collectionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Module)) {
            return false;
        }
        Module other = (Module) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Module[ name=" + name + " ]";
    }
    
}
