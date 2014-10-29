/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datasource;

import entity.AccountDTO;
import entity.ClientDTO;

/**
 *
 * @author Paul
 */
public class DatasourceFacade implements IDatasourceFacade {

    private final FileHandler fh;
    
    public DatasourceFacade() {
        fh = new FileHandler();
    }
    //what does the withdraw method do here because you do not have an ammount
    @Override
    public void withdraw(String clientNumber, float amount, String accountNumber) {
        ClientDTO cDTO = fh.readClientFromFile(clientNumber);
        float balance = cDTO.getAccount().getBalance();
        balance = balance - amount;
        cDTO.setBalance(balance);
        fh.writeClient(cDTO);
    }

    @Override
    public ClientDTO findClient(String clientNumber) {
        return fh.readClientFromFile(clientNumber);
    }

    @Override
    public ClientDTO withdraw(ClientDTO cDTO, float amount) {
        AccountDTO aDTO = cDTO.getAccount();
        if (aDTO.getBalance()>=-999.99F) {
        aDTO.setBalance(aDTO.getBalance()-amount);
        cDTO.setBalance(aDTO.getBalance());
        fh.writeClient(cDTO);
        return cDTO;
        }
        else
            return null;
    }
    
}
