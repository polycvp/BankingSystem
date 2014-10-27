/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datasource;

import entity.AccountDTO;
import entity.ClientDTO;
import java.io.File;

/**
 *
 * @author Paul
 */
public class FileHandler {
    public File readClientFromFile(File f) {
        return f;
    }
    
    public File readAccountFromFile(File f) {
        return f;
    }
    //ambiguity in naming in the Client - ClientDTO
    public void writeClient(ClientDTO client) {
        
    }
    //same as above
    public void writeAccount(AccountDTO account) {
        
    }
    //name missing
    public File createFile() {
        return new File("name");
    }
}
