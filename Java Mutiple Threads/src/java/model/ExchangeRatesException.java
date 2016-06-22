/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Mac
 */
public class ExchangeRatesException extends Exception {
    private CurrencyDTO fromCurrency;
    private CurrencyDTO toCurrency;

    public ExchangeRatesException(CurrencyDTO fromCurrency, CurrencyDTO toCurrency) {
        super("Exchange rates not found");
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }

    public CurrencyDTO getFromCurrency() {
        return fromCurrency;
    }

    public CurrencyDTO getToCurrency() {
        return toCurrency;
    }
}