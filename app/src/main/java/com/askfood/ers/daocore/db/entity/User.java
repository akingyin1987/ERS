package com.askfood.ers.daocore.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/10/16.
 */

@Entity(nameInDb = "tb_user")
public class User  implements Serializable {

    private static final long serialVersionUID = 1541730425232707959L;
    @Id(autoincrement = true)
    public   Long  localId;


    @Property
    public    String    name;


    @Generated(hash = 743497018)
    public User(Long localId, String name) {
        this.localId = localId;
        this.name = name;
    }


    @Generated(hash = 586692638)
    public User() {
    }


    public Long getLocalId() {
        return this.localId;
    }


    public void setLocalId(Long localId) {
        this.localId = localId;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
