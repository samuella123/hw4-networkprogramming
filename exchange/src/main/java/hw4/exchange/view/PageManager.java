/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4.exchange.view;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import hw4.exchange.controller.ExchangerFacade;
import hw4.exchange.model.RateDTO;

/**
 *
 * @author Samie
 */
@Named(value = "pageManager")
@ConversationScoped
public class PageManager implements Serializable {
    @EJB
    private ExchangerFacade exchangerFacade;
    
    private RateDTO currentRate;
    
    private Double newAmount;
    private String newFromRate;
    private String newToRate;
    
    private Double amount;
    private Double result;
    
    private Exception exchangeFailure;
    private String[] currs;
    
    @Inject
    private Conversation conversation;
    
    public void initialize(){
        currs = exchangerFacade.findAllCurrencies();
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
        exchangeFailure = e;
    }


    /**
     * @return <code>true</code> if the latest transaction succeeded, otherwise
     * <code>false</code>.
     */
    public boolean getSuccess() {
        return exchangeFailure == null;
    }

    /**
     * Returns the latest thrown exception.
     */
    public Exception getException() {
        return exchangeFailure;
    }


    public void changeCurrency() {
        try {
            startConversation();
            exchangeFailure = null;
            result = exchangerFacade.changeCurrency(newAmount,currentRate);
            amount = newAmount;
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void findRate() {
        try {
            startConversation();
            exchangeFailure = null;
            currentRate = exchangerFacade.findRate(newFromRate,newToRate);
        } catch (Exception e) {
            handleException(e);
        }
    }


    public void setNewToRate(String newToRate) {
        this.newToRate = newToRate;
    }
    
     /**
     * Never used but JSF does not support write-only properties.
     */
    public String getNewToRate() {
        return null;
    }
    
    
    public void setNewFromRate(String newFromRate) {
        this.newFromRate = newFromRate;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getNewFromRate() {
        return null;
    }
    
        public void setNewAmount(Double newAmount) {
        this.newAmount = newAmount;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public Double getNewAmount() {
        return null;
    }



    public RateDTO getCurrentRate() {
        return currentRate;
    }
    

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public Double getAmount() {
        return amount;
    }
    

    public Double getResult() {
        return result;
    }
    
    public String[] getCurrs() {
        initialize();
        return currs;
    }
    
}
