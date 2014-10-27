/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datasource;

import entity.ClientDTO;

/**
 *
 * @author Paul
 */
public class DatasourceFacade implements IDatasourceFacade 
{
    private DataStorage data;
    
    public DatasourceFacade()
    {
    
    }
    
    public DatasourceFacade(DataStorage data)
    {
        this.data = data;
    }

    @Override
    public void withdraw(String accountNumber, float amount, String clientNumber) 
    {
        data.withdraw(accountNumber, amount, clientNumber);
    }

    @Override
    public ClientDTO findClient(String clientNumber) 
    {
        return data.findClient(clientNumber);
    }
    
}
