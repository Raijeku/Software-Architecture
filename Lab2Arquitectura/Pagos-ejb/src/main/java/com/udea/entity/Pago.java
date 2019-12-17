/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Davquiroga
 */
@Entity
@Table(name = "pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p")
    , @NamedQuery(name = "Pago.findByNombre", query = "SELECT p FROM Pago p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Pago.findByEmail", query = "SELECT p FROM Pago p WHERE p.email = :email")
    , @NamedQuery(name = "Pago.findByCardNumber", query = "SELECT p FROM Pago p WHERE p.cardNumber = :cardNumber")
    , @NamedQuery(name = "Pago.findByCvv", query = "SELECT p FROM Pago p WHERE p.cvv = :cvv")
    , @NamedQuery(name = "Pago.findByCardType", query = "SELECT p FROM Pago p WHERE p.cardType = :cardType")
    , @NamedQuery(name = "Pago.findByValorTransaccion", query = "SELECT p FROM Pago p WHERE p.valorTransaccion = :valorTransaccion")
    , @NamedQuery(name = "Pago.findByVencimiento", query = "SELECT p FROM Pago p WHERE p.vencimiento = :vencimiento")
    , @NamedQuery(name = "Pago.findById", query = "SELECT p FROM Pago p WHERE p.id = :id")})
public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "card_number")
    private double cardNumber;
    @Basic(optional = false)
    @Column(name = "cvv")
    private double cvv;
    @Basic(optional = false)
    @Size(min = 1, max = 15)
    @Column(name = "card_type")
    private String cardType;
    @Basic(optional = false)
    @Column(name = "valor_transaccion")
    private double valorTransaccion;
    @Column(name = "vencimiento")
    @Temporal(TemporalType.DATE)
    private Date vencimiento;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;

    public Pago() {
    }

    public Pago(Short id) {
        this.id = id;
    }

    public Pago(String nombre, String email, double cardNumber, double cvv, String cardType, double valorTransaccion, Date vencimiento, Short id) {
        this.nombre = nombre;
        this.email = email;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.cardType = cardType;
        this.valorTransaccion = valorTransaccion;
        this.vencimiento = vencimiento;
        this.id = id;
    }

    public Pago(Short id, String nombre, String email, double cardNumber, double cvv, String cardType, double valorTransaccion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.cardType = cardType;
        this.valorTransaccion = valorTransaccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(double cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getCvv() {
        return cvv;
    }

    public void setCvv(double cvv) {
        this.cvv = cvv;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public double getValorTransaccion() {
        return valorTransaccion;
    }

    public void setValorTransaccion(double valorTransaccion) {
        this.valorTransaccion = valorTransaccion;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
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
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udea.entity.Pago[ id=" + id + " ]";
    }
    
}
