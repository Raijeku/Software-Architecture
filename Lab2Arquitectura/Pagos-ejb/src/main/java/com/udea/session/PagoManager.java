/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.session;

import com.udea.entity.Pago;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Davquiroga
 */
@Stateless
public class PagoManager implements PagoManagerLocal {

    @PersistenceContext(unitName = "com.udea_Pagos-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    @Override
    public List<Pago> getAllPagos() {
        Query query = em.createNamedQuery("Pago.findAll");
        return query.getResultList();
    }
    
    @Override
    public Pago update(Pago pago) {
        /*double cardNumber=pago.getCardNumber()%100000;
        String cardType="";
        if (cardNumber>11111 && cardNumber<=22222) {
            cardType="American Express";
        } else if (cardNumber>33334 && cardNumber<=44444) {
            cardType="Diners";
        } else if (cardNumber>55555 && cardNumber<=66666) {
            cardType="Visa";
        } else if (cardNumber>77777 && cardNumber<=88888) {
            cardType="Mastercard";
        }
        pago.setCardType(cardType);*/
        return em.merge(pago);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    
}
