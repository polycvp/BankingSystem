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
public interface IDatasourceFacade {
    public void withdraw(String accountNumber,float amount,String clientNumber); //made acording to the DSD
    public ClientDTO withdraw(ClientDTO cDTO,float amount); //irrelevant method according to DCD
    public ClientDTO findClient(String clientNumber);
}
