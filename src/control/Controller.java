/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import entity.ClientDTO;

/**
 *
 * @author Paul
 */
public class Controller implements IController
{
    private WithdrawManager wm;
    
    public Controller()
    {
        wm = new WithdrawManager();
    }

    @Override
    public ClientDTO withdraw(String accountNumber, float amount, String clientNumber) {
        return wm.withdraw(accountNumber, amount, clientNumber);
    }
    
}
