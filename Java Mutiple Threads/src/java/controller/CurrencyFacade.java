/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Currency;
import model.CurrencyDTO;
import model.ExchangeRates;
import model.ExchangeRatesException;

/**
 *
 * @author Mac
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CurrencyFacade implements Serializable {

    @PersistenceContext(unitName = "currencyPU")
    private EntityManager em;

    public List<CurrencyDTO> getCurrencyList() {
        return em.createNamedQuery(Currency.GET_ALL_CURRENCIES_REQUEST, CurrencyDTO.class).getResultList();
    }

    public float CurrencyConverter(String fromCurrencyUnit, String toCurrencyUnit, float inputMoney) throws ExchangeRatesException {
        Currency toCurrency = em.createNamedQuery(Currency.GET_CURRENCY_BY_CurrencyUnit_REQUEST, Currency.class).setParameter("currencyUnit", toCurrencyUnit).getSingleResult();
        System.out.println("toCurrency "+toCurrency);
        Currency fromCurrency = em.createNamedQuery(Currency.GET_CURRENCY_BY_CurrencyUnit_REQUEST, Currency.class).setParameter("currencyUnit", fromCurrencyUnit).getSingleResult();
        System.out.println("fromCurrency "+fromCurrency);
        List<ExchangeRates> resList = em.createNamedQuery(ExchangeRates.GET_EXCHANGE_RATE_REQUEST, ExchangeRates.class).
                setParameter("from", fromCurrency).
                setParameter("to", toCurrency).
                getResultList();
        System.out.println("resList size $$"+resList.size());
        if (resList.isEmpty()) {
            throw new ExchangeRatesException(fromCurrency, toCurrency);
        } else {
            for(int i=0;i<resList.size();i++){
            System.out.println("$"+resList.get(i).getRates());
            }
            ExchangeRates rates = resList.get(0);
            return inputMoney * rates.getRates();
        }
    }

}
