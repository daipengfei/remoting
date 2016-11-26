package com.enniu.remoting;

import java.io.Serializable;
import java.util.Map;

/*********************************
 *                               *
 Created by daipengfei on 16/11/25.
 *                               *
 ********************************/

public class SerialClass  implements Serializable{
    private static final long serialVersionUID = 7251117475076305277L;

    private String name;

    private Long id;

    private Map<String,String> features;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, String> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, String> features) {
        this.features = features;
    }


}
