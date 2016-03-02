/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author flycktm
 */
@Entity
@Table(name = "datapoints")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datapoint.findAll", query = "SELECT d FROM Datapoint d"),
    @NamedQuery(name = "Datapoint.findByNode", query = "SELECT d FROM Datapoint d WHERE d.datapointPK.node = :node"),
    @NamedQuery(name = "Datapoint.findByOwner", query = "SELECT d FROM Datapoint d WHERE d.datapointPK.owner = :owner"),
    @NamedQuery(name = "Datapoint.findByTime", query = "SELECT d FROM Datapoint d WHERE d.datapointPK.time = :time"),
    @NamedQuery(name = "Datapoint.findByData", query = "SELECT d FROM Datapoint d WHERE d.data = :data")})
public class Datapoint implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DatapointPK datapointPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    private int data;
    @JoinColumns({
        @JoinColumn(name = "node", referencedColumnName = "name", insertable = false, updatable = false),
        @JoinColumn(name = "owner", referencedColumnName = "owner", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Node node1;

    public Datapoint() {
    }

    public Datapoint(DatapointPK datapointPK) {
        this.datapointPK = datapointPK;
    }

    public Datapoint(DatapointPK datapointPK, int data) {
        this.datapointPK = datapointPK;
        this.data = data;
    }

    public Datapoint(String node, String owner, Date time) {
        this.datapointPK = new DatapointPK(node, owner, time);
    }

    public DatapointPK getDatapointPK() {
        return datapointPK;
    }

    public void setDatapointPK(DatapointPK datapointPK) {
        this.datapointPK = datapointPK;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
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
        hash += (datapointPK != null ? datapointPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datapoint)) {
            return false;
        }
        Datapoint other = (Datapoint) object;
        if ((this.datapointPK == null && other.datapointPK != null) || (this.datapointPK != null && !this.datapointPK.equals(other.datapointPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Datapoint[ datapointPK=" + datapointPK + " ]";
    }
    
}
