/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datasource;

import entity.ClientDTO;
import java.io.File;

/**
 *
 * @author Paul
 */
public class BankStorageAPI implements IBankStorageAPI {

    private FileHandler fh = new FileHandler();
    
    //what does the withdraw method do here because you do not have an ammount
    @Override
    public void withdraw(ClientDTO c, float amount) {
        ClientDTO cDTO = fh.readClientFromFile(c.getClientNumber());
        float balance = cDTO.getAccount().getBalance();
        balance = balance - amount;
        cDTO.setBalance(balance);
        fh.writeClient(cDTO);
    }

    @Override
    public ClientDTO findClient(String clientNumber) {
        fh.readClientFromFile(new File(clientNumber));
        return new ClientDTO();
    }

    @Override
    public void withdraw(ClientDTO c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
