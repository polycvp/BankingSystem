/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasource;

import entity.AccountDTO;
import entity.ClientDTO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paul
 */
public class FileHandler {

    //FileHandler or BankStorageAPI should have a reference to a single file

    private File homeFile;

    public FileHandler() {
        homeFile = this.createFile();
    }

    private boolean doesClientExist(String clientNumber) {
         FileReader fr = null;
         boolean exists = false;
        try {
            fr = new FileReader(homeFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                int i = line.indexOf(";");
                String cNo = line.substring(0, i - 1);
                if (cNo.equalsIgnoreCase(clientNumber)) {
                  exists = true;
            }
            br.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            exists = false;
                }
        return exists;
    }
    
    public ClientDTO readClientFromFile(String clientNumber) {
        FileReader fr = null;
        ClientDTO cDTO = new ClientDTO();
        try {
            if (!doesClientExist(clientNumber))
                throw new Exception("Client not found!");
            
            fr = new FileReader(homeFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                int i = line.indexOf(";");
                String cNo = line.substring(0, i - 1);
                if (cNo.equalsIgnoreCase(clientNumber)) {
                    cDTO.setClientNumber(cNo);
                    line = line.substring(0, i);
                    i = line.indexOf(";");
                    cDTO.setName(line.substring(0, i - 1));
                    line = line.substring(0, i);
                    AccountDTO aDTO = new AccountDTO();
                    i = line.indexOf(";");
                    aDTO.setAccountNumber(line.substring(0, i - 1));
                    line = line.substring(0, i);
                    i = line.indexOf(";");
                    aDTO.setBalance(Float.parseFloat(line.substring(0, i - 1)));
                    cDTO.addAccount(aDTO);
                } else {
                }
            }
            br.close();
            return cDTO;
        } catch (Exception ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //it should return a client not a file and it should recieve the identifier of the client
    public File readClientFromFile(File f) {
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            return f;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //it should return an account not a file and it should recieve the identifier of the account
    public File readAccountFromFile(File f) {
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            return f;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //ambiguity in naming in the Client - ClientDTO
    //and no dependency to the client DTO
    //how does this method write the client without a file passed to it
    public void writeClient(ClientDTO client) {
        try {
            if (doesClientExist(client.getClientNumber())) {
                //if client already exists than update the client information with the new one
            }
            else {
            FileWriter fw = new FileWriter(homeFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(client.getClientNumber()+";"+client.getName()+";"+client.getAccount().getAccountNumber()+";"+
                    client.getAccount().getBalance());
            bw.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //same as above

    public void writeAccount(AccountDTO account) {
        try {
            FileWriter fw = new FileWriter(this.createFile());
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //name and path for the file are missing as 

    private File createFile() {
        return new File("BankSystem");
    }
}
