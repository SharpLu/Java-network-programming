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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Mac
 */
@NamedQueries({
    @NamedQuery(name = ExchangeRates.GET_EXCHANGE_RATE_REQUEST,
            query = "SELECT r "
            + "FROM ExchangeRates r "
            + "WHERE "
            + "(r.fromCurrency = :from) "
            + "AND "
            + "(r.toCurrency = :to)")
})
@Entity
public class ExchangeRates implements ExchangeRatesDTO, Serializable {

    public static final String GET_EXCHANGE_RATE_REQUEST = "ExchangeRates_getExchangeRates";
    private static final long serialVersionUID = 123L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Currency fromCurrency;
    @ManyToOne
    private Currency toCurrency;
    private float rates;

    public ExchangeRates() {
    }

    public ExchangeRates(Currency fromCurrency, Currency toCurrency, float rates) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rates = rates;
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
     * @return the fromCurrency
     */
    public Currency getFromCurrency() {
        return fromCurrency;
    }

    /**
     * @param fromCurrency the fromCurrency to set
     */
    public void setFromCurrency(Currency fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    /**
     * @return the toCurrency
     */
    public Currency getToCurrency() {
        return toCurrency;
    }

    /**
     * @param toCurrency the toCurrency to set
     */
    public void setToCurrency(Currency toCurrency) {
        this.toCurrency = toCurrency;
    }

    /**
     * @return the rates
     */
    public float getRates() {
        return rates;
    }

    /**
     * @param rates the rates to set
     */
    public void setRates(float rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "converter.model.ExchangeRates [ id=" + id + " ]";
    }

}
