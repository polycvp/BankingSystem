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
public interface IController 
{
    public ClientDTO withdraw(String accountNumber, float amount, String clientNumber);
}
