/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rick
 */
public class ClientsListDTO implements Serializable {
    
    private static final long serialVersionUID = 2L;
    
    private List<ClientDTO> clients;
    
    public ClientsListDTO() {
        clients = new ArrayList<>();
    }
    
    public void addClientToList(ClientDTO c) {
        clients.add(c);
    }
    
    public void removeByClientNumber(String clientNumber) {
        ClientDTO delete = null;
        for (ClientDTO c : clients) {
            if (c.getClientNumber().equalsIgnoreCase(clientNumber)) {
                delete = c;
            }
        }
        if (delete != null) {
            clients.remove(delete);
    
        }
    }
    
    public List<ClientDTO> getClients() {
        return clients;
    }
    
    public void setClients(List<ClientDTO> clients) {
        this.clients = clients;
    }
    
}
