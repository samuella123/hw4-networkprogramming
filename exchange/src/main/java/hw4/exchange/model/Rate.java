/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4.exchange.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Samie
 */
@Entity
public class Rate implements RateDTO,Serializable {
    @Id
    private String id;
    private String firstCurrency;
    private String secondCurrency;
    private Double dRate;


    public Rate() {
    }


    public Rate(Double rate, String fromRate, String toRate) {
        this.firstCurrency = fromRate;
        this.secondCurrency = toRate;
        this.dRate = rate;
        this.id = fromRate+toRate;
    }
    
    public Double changeCurrency(Double amount) {
        return amount*dRate;
    }

    @Override
    public String getFirstCurr() {
        return firstCurrency;
    }

    @Override
    public String getSecondCurr() {
        return secondCurrency;
    }

    @Override
    public Double getRate() {
        return dRate;
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
        if (!(object instanceof Rate)) {
            return false;
        }
        Rate other = (Rate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hw4.exchange.model.Rate[ id=" + id + " ]";
    }
    
}
