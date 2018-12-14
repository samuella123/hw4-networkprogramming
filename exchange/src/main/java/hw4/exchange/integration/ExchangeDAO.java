/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4.exchange.integration;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import hw4.exchange.model.Rate;
import java.util.*;

/**
 *
 * @author Samie
 */
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class ExchangeDAO {
    @PersistenceContext(unitName = "exchangePU")
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Rate findRateByName(String fromRate,String toRate) {
        Rate rate = em.find(Rate.class, fromRate+toRate);
        if (rate == null) {
            throw new EntityNotFoundException("No Rate Found: " + fromRate + " to " + toRate);
        }
        return rate;
    }
    
    public String[] findAllCurrs() {
        List<Rate> listRates = em.createQuery("SELECT p FROM Rate p").getResultList();
        ArrayList<String> result = new ArrayList<String>();
        for(int i=0;i<listRates.size();i++) {
            if(!result.contains(listRates.get(i).getFirstCurr())) {
                result.add(listRates.get(i).getFirstCurr());
            }
        }
        return result.toArray(new String[result.size()]);
    }
}
