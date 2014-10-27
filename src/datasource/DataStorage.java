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
public class DataStorage {
    
    private IBankStorageAPI bsApi;
    
    public DataStorage(IBankStorageAPI b_api) {
        bsApi = b_api;
    }
    
    public void withdraw(String accountNumber,float amount,String clientNumber) {
        //bsApi.withdraw(findClient(clientNumber));
        bsApi.withdraw(accountNumber, amount, clientNumber);
    }
    
    public ClientDTO findClient(String clientNumber) {
        return bsApi.findClient(clientNumber);
    }
    
}
