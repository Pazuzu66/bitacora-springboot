package com.bitacora.bitacora.documents;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Bitacora")
public class Bitacora {
    @Id
    private String id;
    @Pattern(regexp = "CREATED|UPDATED|DELETED|TEST")
    private String accion;
    @NotEmpty    
    private String body;
    @NotEmpty
    private String timestamp;

    public Bitacora(String id, String accion, String body, String timestamp){
        this.id = id;
        this.accion = accion;
        this.body = body;
        this.timestamp = timestamp;
    }


    public Bitacora() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccion() {
        return this.accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
