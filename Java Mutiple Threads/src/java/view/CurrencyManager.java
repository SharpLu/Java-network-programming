/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CurrencyFacade;
import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.CurrencyDTO;
import model.ExchangeRatesException;

/**
 *
 * @author Mac
 */
@Named(value = "CurrencyManager")
@ConversationScoped
public class CurrencyManager implements Serializable {

    private static final long serialVersionUID = 16247164405L;
    @EJB
    private CurrencyFacade currencyFacade;
    @Inject
    private Conversation conversation;
    private String toCurrencyString;
    private String fromCurrencyString;
    private float inputMoney;
    private float result;
    private List<CurrencyDTO> currencyList;
    private Exception conversionFailure;

    public float getInputMoney() {
        return inputMoney;
    }

    public void setInputMoney(float inputMoney) {
        this.inputMoney = inputMoney;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public String getFromCurrencyString() {
        return fromCurrencyString;
    }

    public void setFromCurrencyString(String fromCurrency) {
        this.fromCurrencyString = fromCurrency;
    }

    public String getToCurrencyString() {
        return toCurrencyString;
    }

    public void setToCurrencyString(String toCurrency) {
        this.toCurrencyString = toCurrency;
    }

    public boolean isSuccess() {
        return conversionFailure == null;
    }

    public List<CurrencyDTO> getCurrencyList() {
        currencyList = currencyFacade.getCurrencyList();
        return currencyList;
    }

    public void setCurrencyList(List<CurrencyDTO> currencyList) {
        this.currencyList = currencyList;
    }

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        conversionFailure = e;
    }

    public Exception getException() {
        return conversionFailure;
    }

    public void currencyConveter() {
        startConversation();
        try {
            result = currencyFacade.CurrencyConverter(getCurrencyUnit(fromCurrencyString), getCurrencyUnit(toCurrencyString), inputMoney);
        } catch (ExchangeRatesException ex) {
            handleException(ex);
            result =0;
        }
    }

    private String getCurrencyUnit(String getString) {
        StringTokenizer st = new StringTokenizer(getString, "()");
        st.nextToken();
        String CurrencyUnit = st.nextToken().trim();
        System.out.println(CurrencyUnit);
        return CurrencyUnit;
    }
}
