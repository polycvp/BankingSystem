/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import datasource.DatasourceFacade;
import entity.ClientDTO;

/**
 *
 * @author Paul
 */
public class Controller implements IController
{
    //private WithdrawManager wm;
    private DatasourceFacade bsa;
    
    public Controller()
    {
        //wm = new WithdrawManager();
        bsa = new DatasourceFacade();
    }

    @Override
    public ClientDTO withdraw(String accountNumber, float amount, String clientNumber) {
        ClientDTO cDTO = bsa.findClient(clientNumber);
        return bsa.withdraw(cDTO,amount);
        //wm.withdraw(accountNumber, amount, clientNumber);
    }
    
}
