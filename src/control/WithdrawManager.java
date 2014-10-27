/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import datasource.DatasourceFacade;
import entity.AccountDTO;
import entity.ClientDTO;

/**
 *
 * @author Paul
 */
public class WithdrawManager 
{
    private DatasourceFacade df;
    
    public WithdrawManager()
    {
        df = new DatasourceFacade();
    }
    
    public ClientDTO withdraw(String accountNumber, float amount, String clientNumber)
    {
        ClientDTO cDTO = df.findClient(clientNumber);
        AccountDTO aDTO = null;
        //check balance here
        if (cDTO.getAccount().getBalance()>=-999) {
            df.withdraw(accountNumber, amount, cDTO.getClientNumber());
            //this should be done and updated in the file handler
            aDTO = cDTO.getAccount();
            aDTO.setBalance(amount);
        }
       return cDTO;
    }
}
