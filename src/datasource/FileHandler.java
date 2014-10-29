/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasource;

import entity.AccountDTO;
import entity.ClientDTO;
import entity.ClientsListDTO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
    }

//    private boolean doesClientExist(String clientNumber) {
//        FileReader fr = null;
//        boolean exists = false;
//        try {
//            fr = new FileReader(homeFile);
//            BufferedReader br = new BufferedReader(fr);
//            String line;
//            while ((line = br.readLine()) != null) {
//                int i = line.indexOf(";");
//                String cNo = line.substring(0, i - 1);
//                if (cNo.equalsIgnoreCase(clientNumber)) {
//                    exists = true;
//                }
//                br.close();
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
//            exists = false;
//        }
//        return exists;
//    }
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

    // 
    public ClientDTO readClientFromFile(String clientNumber) {
        ClientDTO cDTO = null;
        for (ClientDTO c : deserializeClientsList().getClients()) {
            if (c.getClientNumber().equalsIgnoreCase(clientNumber)) {
                cDTO = c;
            }
        }
        return cDTO;
    }
//    public ClientDTO readClientFromFile(String clientNumber) {
//        FileReader fr = null;
//        ClientDTO cDTO = new ClientDTO();
//        try {
//            if (!doesClientExist(clientNumber)) {
//                throw new Exception("Client not found!");
//            }
//
//            fr = new FileReader(homeFile);
//            BufferedReader br = new BufferedReader(fr);
//            String line;
//            while ((line = br.readLine()) != null) {
//                int i = line.indexOf(";");
//                String cNo = line.substring(0, i - 1);
//                if (cNo.equalsIgnoreCase(clientNumber)) {
//                    cDTO.setClientNumber(cNo);
//                    line = line.substring(0, i);
//                    i = line.indexOf(";");
//                    cDTO.setName(line.substring(0, i - 1));
//                    line = line.substring(0, i);
//                    AccountDTO aDTO = new AccountDTO();
//                    i = line.indexOf(";");
//                    aDTO.setAccountNumber(line.substring(0, i - 1));
//                    line = line.substring(0, i);
//                    i = line.indexOf(";");
//                    aDTO.setBalance(Float.parseFloat(line.substring(0, i - 1)));
//                    cDTO.addAccount(aDTO);
//                } else {
//                }
//            }
//            br.close();
//            return cDTO;
//        } catch (Exception ex) {
//            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        } finally {
//            try {
//                fr.close();
//            } catch (IOException ex) {
//                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }

//    //it should return a client not a file and it should recieve the identifier of the client
//    public File readClientFromFile(File f) {
//        FileReader fr = null;
//        try {
//            fr = new FileReader(f);
//            return f;
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        } finally {
//            try {
//                fr.close();
//            } catch (IOException ex) {
//                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
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
//    public File readAccountFromFile(File f) {
//        FileReader fr = null;
//        try {
//            fr = new FileReader(f);
//            return f;
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        } finally {
//            try {
//                fr.close();
//            } catch (IOException ex) {
//                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }

    //ambiguity in naming in the Client - ClientDTO
    //and no dependency to the client DTO
    //how does this method write the client without a file passed to it
//    public void writeClient(ClientDTO client) {
//        try {
//            if (doesClientExist(client.getClientNumber())) {
//                //if client already exists than update the client information with the new one
//            } else {
//                FileWriter fw = new FileWriter(homeFile);
//                BufferedWriter bw = new BufferedWriter(fw);
//                bw.write(client.getClientNumber() + ";" + client.getName() + ";" + client.getAccount().getAccountNumber() + ";"
//                        + client.getAccount().getBalance());
//                bw.newLine();
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
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
//
//    public void writeAccount(AccountDTO account) {
//        try {
//            FileWriter fw = new FileWriter(this.createFile());
//        } catch (IOException ex) {
//            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    //name and path for the file are missing as 
    private File createFile() {
        serializeClientsList(new ClientsListDTO());
        return new File("clientslist.dat");
    }
}
