/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4.exchange.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityNotFoundException;
import hw4.exchange.integration.ExchangeDAO;
import hw4.exchange.model.*;

/**
 *
 * @author Samie
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class ExchangerFacade {
    @EJB ExchangeDAO exchangeDB;

    public RateDTO findRate(String fromRate, String toRate) throws Exception{
        if(fromRate==null || toRate==null)
        {
            throw new Exception("Select both currencies then click search.");
        }
        if(fromRate.equals(toRate))
        {
            throw new Exception("2 currencies cannot be same.");
        }
        if(fromRate.equals("Select...") || toRate.equals("Select..."))
        {
            throw new Exception("Select both currencies then click search.");
        }
        return exchangeDB.findRateByName(fromRate,toRate);
    }

    public Double changeCurrency(Double inp,RateDTO rate) throws Exception {
        if(rate == null)
        {
            throw new Exception("No currencies are chosen.");
        }
        if(inp == null)
        {
            throw new Exception("You didn't enter any amount.");
        }
        Rate myRate = (Rate)rate;
        return myRate.changeCurrency(inp);
    }
    
    public String[] findAllCurrencies() {
        return exchangeDB.findAllCurrs();
    }
    
}
