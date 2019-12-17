/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controller;

import com.udea.entity.Pago;
import com.udea.session.PagoManager;
import com.udea.session.PagoManagerLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author Davquiroga
 */
@Named(value = "pagoMBean")
@SessionScoped
public class PagoMBean implements Serializable{

    @EJB
    private PagoManagerLocal pagoManager;

    private Pago pago;
    private List<Pago> pagos;
    
    /**
     * Creates a new instance of PagoMBean
     */
    public PagoMBean() {
    }
    
    public List<Pago> getPagos(){
        if ((pagos==null)||(pagos.isEmpty())) {
            refresh();
        }
        return pagos;
    }
    
    public Pago getPago(){
        if (pago==null) {
            pago=new Pago("","",22333,123,"bla",5000,new Date(),(short)1);
        }
        return pago;
    }
    
    public String showPago(Pago pago){
        this.pago=pago;
        return "DETAIL";
    }
    
    public String showForm() {
        return "FORM";
    }
    
    public String update() {
        pago=pagoManager.update(pago);
        return "SAVED";
    }
    
    /*public javax.faces.model.SelectItem[] getPagos() {
        SelectItem[] options=null;
        List<Pago> pagos=pagoManager.getAllPagos();
        if (pagos!=null && pagos.size()>0) {
            int i=0;
            options=new SelectItem[pagos.size()];
            for (Pago pago : pagos) {
                options[i++] = new SelectItem(pago);
            }
        }
        return options;
    }*/
    
    private void refresh(){
        pagos=pagoManager.getAllPagos();
    }
    
}
