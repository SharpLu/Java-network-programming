/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Mac
 */
@NamedQueries({
    @NamedQuery(name = Currency.GET_ALL_CURRENCIES_REQUEST,
            query = "SELECT c "
            + "FROM Currency c"),
    @NamedQuery(name = Currency.GET_CURRENCY_BY_CurrencyUnit_REQUEST,
            query = "SELECT c "
            + "FROM Currency c "
            + "WHERE c.currencyUnit = :currencyUnit")
})
@Entity
public class Currency implements CurrencyDTO,Serializable{

    public static final String GET_ALL_CURRENCIES_REQUEST = "Currency_getAllCurrencies";
    public static final String GET_CURRENCY_BY_CurrencyUnit_REQUEST = "Currency_getCurrencyByCurrencyUnit";
    private static final long serialVersionUID = 16247164401L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String country;
    private String currencyUnit;

    public Currency() {
    }

    public Currency(int id,String country,String currencyUnit){
    this.id=id;
    this.country=country;
    this.currencyUnit=currencyUnit;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the currencyUnit
     */
    public String getCurrencyUnit() {
        return currencyUnit;
    }

    /**
     * @param currencyUnit the currencyUnit to set
     */
    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }
    
    
        @Override
    public String toString() {
        return country + " (" + currencyUnit + ")";
    }
    
}

