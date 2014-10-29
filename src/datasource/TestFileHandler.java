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
 * @author Rick
 */
public class TestFileHandler {

    public static void main(String[] args) {
        FileHandler fh = new FileHandler();
        System.out.println("File Handler created ...");

        ClientDTO clientDTO = new ClientDTO("Rick", "ABC123", new AccountDTO("ABC123456", 6.66F));
        fh.writeClient(clientDTO);
        System.out.println("Client written ....");

        ClientDTO readClient = fh.readClientFromFile("ABC123");
        System.out.println("Read client from serialized file ...");

        System.out.println("Name: " + readClient.getName() + " Balance: " + readClient.getAccount().getBalance());

        ClientDTO clientDTOLessBalance = new ClientDTO("Rick", "ABC123", new AccountDTO("ABC123456", 6.0F));
        fh.writeClient(clientDTOLessBalance);

        readClient = fh.readClientFromFile("ABC123");
        System.out.println("Read client from serialized file ...");

        System.out.println("Name: " + readClient.getName() + " Balance: " + readClient.getAccount().getBalance());
    }
}
