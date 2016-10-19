/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gm.controles;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;



/**
 *
 * @author gmendoza
 */
@ManagedBean
@RequestScoped
public class ServerTimeBean {
    
    public String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public void updateTime()
    {
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        this.time=sdf.format(new Date());
    }
    
}
