/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasource;

import entity.AccountDTO;
import entity.ClientDTO;
import entity.ClientsListDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paul
 */
public class FileHandler {

    //FileHandler or BankStorageAPI should have a reference to a single file
    //clientNumber;name;accountNumber;balance
    private File homeFile;

    public FileHandler() {
        homeFile = this.createFile();
        writeClient(new ClientDTO("Client1", "c1", new AccountDTO("a1", 6432.00F)));
        writeClient(new ClientDTO("Client2", "c2", new AccountDTO("a1", 4432.00F)));
        writeClient(new ClientDTO("Client3", "c3", new AccountDTO("a1", 9432.00F)));
        writeClient(new ClientDTO("Client4", "c4", new AccountDTO("a1", 1432.00F)));
        writeClient(new ClientDTO("Client5", "c5", new AccountDTO("a1", -1432.00F)));
        writeClient(new ClientDTO("Client6", "c6", new AccountDTO("a1", -432.00F)));
    }

    private boolean doesClientExist(String clientNumber) {
        boolean result = false;
        for (ClientDTO c : deserializeClientsList().getClients()) {
            result = c.getClientNumber().equalsIgnoreCase(clientNumber);
        }
        return result;
    }

    private ClientsListDTO deserializeClientsList() {
        ClientsListDTO clientsList = null;

        if (homeFile.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(new File("clientslist.dat"));
            } catch (FileNotFoundException ex) {
            }

            ObjectInputStream reader = null;
            try {
                reader = new ObjectInputStream(fis);
                try {
                    clientsList = (ClientsListDTO) reader.readObject();
                } catch (ClassNotFoundException ex) {
                    System.out.println("Caught ClassNotFoundException");
                    ex.printStackTrace();
                }
            } catch (IOException ex) {
                System.out.println("Caught IOException");
                ex.printStackTrace();
            } finally {
                try {
                    fis.close();
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return clientsList;
    }

    private void serializeClientsList(ClientsListDTO cl) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("clientslist.dat"));
            out.writeObject(cl);
            out.close();
        } catch (IOException ex) {
            System.out.println("Error serializing clients list dto");
            ex.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ClientDTO readClientFromFile(String clientNumber) {
        ClientDTO cDTO = null;
        for (ClientDTO c : deserializeClientsList().getClients()) {
            if (c.getClientNumber().equalsIgnoreCase(clientNumber)) {
                cDTO = c;
            }
        }
        return cDTO;
    }

    // Takes File handle of serialized account dto file as param
    public AccountDTO readAccountFromFile(File f) {
        AccountDTO aDTO = null;

        if (f.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(f);
            } catch (FileNotFoundException ex) {
            }

            ObjectInputStream reader = null;
            try {
                reader = new ObjectInputStream(fis);
                try {
                    aDTO = (AccountDTO) reader.readObject();
                } catch (ClassNotFoundException ex) {
                    System.out.println("Caught ClassNotFoundException");
                    ex.printStackTrace();
                }
            } catch (IOException ex) {
                System.out.println("Caught IOException");
                ex.printStackTrace();
            } finally {
                try {
                    fis.close();
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return aDTO;
    }

    public void writeClient(ClientDTO client) {
        ClientsListDTO clientsList = deserializeClientsList();

        if (!doesClientExist(client.getClientNumber())) {
            clientsList.addClientToList(client);
        } else {
            clientsList.removeByClientNumber(client.getClientNumber());
            clientsList.addClientToList(client);
        }
        
        serializeClientsList(clientsList);
    }

    // Serializes account dto to account.dat file
    public void writeAccount(AccountDTO account) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("account.dat"));
            out.writeObject(account);
            out.close();
        } catch (IOException ex) {
            System.out.println("Error serializing account dto");
            ex.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //name and path for the file are missing as 
    private File createFile() {
        serializeClientsList(new ClientsListDTO());
        return new File("clientslist.dat");
    }
}
