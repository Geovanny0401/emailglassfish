/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gm.javamails;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author gmendoza
 */
@ManagedBean
@RequestScoped
public class MailBeans {
   
    @Resource(name="Email")
    private Session session;
    
    private String to;
    private String subject;
    private String descr;
    
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
    
    public String submitEmail()
    {
        Address toAddress = null;
        
        System.out.println("Comienzo de Envio");
        Message msg = new MimeMessage(session);
        
        try{
            msg.setContent(getDescr(), "text/plain");
            msg.setSubject(getSubject());
            toAddress = new InternetAddress(getTo());
            msg.setRecipient(Message.RecipientType.TO, toAddress);
            msg.saveChanges();
            Transport.send(msg);
            }catch(MessagingException e)
                {
                    return "emailFal";
            }
        System.out.println("Envio Exitoso");
        return "emailOk";
    }  

   public void validateEmail(FacesContext context, UIComponent toValidate, Object value) {
        String message = "";
        String email = (String) value;
        if ((email == null) || (email.equals(""))) {
            ((UIInput) toValidate).setValid(false);
            message = "Email Requerido";
            context.addMessage(toValidate.getClientId(context), new FacesMessage(message));
        } else if ((!email.contains("@")) || (!email.contains("."))) {
            ((UIInput) toValidate).setValid(false);
            message = "Email Invalido";
            context.addMessage(toValidate.getClientId(context), new FacesMessage(message));
        }
    }

    
    
}
