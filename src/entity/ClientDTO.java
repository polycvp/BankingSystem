/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author Paul
 */
public class ClientDTO 
{
    private String name;
    private String clientNumber;
    private AccountDTO account;
    
    public ClientDTO() {
        
    }
    
    public ClientDTO(String name, String clientNumber, AccountDTO account)
    {
        this.name = name;
        this.clientNumber = clientNumber;
        this.account = account;
    }
    
    public void setBalance(float amount)
    {
        account.setBalance(amount);
    }
    
    public AccountDTO getAccount()
    {
        return account;
    }
    
    public void addAccount(AccountDTO aDTO)
    {
        this.account = aDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }
    
}
