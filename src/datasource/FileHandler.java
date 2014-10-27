/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datasource;

import entity.AccountDTO;
import entity.ClientDTO;
import java.io.File;
import java.io.FileNotFoundException;
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
            FileWriter fw = new FileWriter(this.createFile());
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
