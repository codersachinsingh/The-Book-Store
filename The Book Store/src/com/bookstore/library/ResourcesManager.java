package com.bookstore.library;

import java.io.*;
import java.util.ArrayList;

public class ResourcesManager implements Runnable  {
	
	private final File appDataDir = new File("C:/Users/admin/AppData/Local/The Book Store/Data");
	private final File resourceFile = new File("C:/Users/admin/AppData/Local/The Book Store/Data/resources.res");
    private final File masterPswdFile = new File("C:/Users/admin/AppData/Local/The Book Store/Data/master.pwd");
	//Applicatoin Storage for books and customer details
	private ArrayList<BookStock> allBookstock;
	private ArrayList<Customer> allCustomers;

	ResourcesManager() {
		//Create Software Data directories if not present
		if (!appDataDir.exists())
			appDataDir.mkdirs();

		if (!isResourcesAvailable()) {

		}
    }

	//Getters and Setters for resources
	public ArrayList<BookStock> getAllBookstock() {
		return allBookstock;
	}
	public void setAllBookstock(ArrayList<BookStock> allBookstock) {
		this.allBookstock = allBookstock;
	}
	public ArrayList<Customer> getAllCustomers() {
		return allCustomers;
	}
	public void setAllCustomer(ArrayList<Customer> allCustomer) {
		this.allCustomers = allCustomer;
	}

	//getter for masterpassword file
    public File getMasterPswdFile() {
        return masterPswdFile;
    }

	// method to save current resources to "resources.res" file.
	public void saveResources() {
		ArrayList<Object> data = new ArrayList<>();
		data.add(allBookstock); // books resoureces are at index 0
		data.add(allCustomers); // customer resoureces are at index 1
		
 		try (FileOutputStream outfile = new FileOutputStream(resourceFile);
 				ObjectOutputStream oos = new ObjectOutputStream(outfile)) {
 				oos.writeObject(data);
 				
 		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	//method to load the disk file resources to applicaton (generally called on application startup)
	
	@SuppressWarnings("unchecked")
	public boolean loadResources() {
	    /*
	    * Load resources from file if available
	    * */

	    if (isResourcesAvailable()) {
            ArrayList<Object> deser;
            try (FileInputStream filein = new FileInputStream(resourceFile);
                 ObjectInputStream ois = new ObjectInputStream(filein)) {
                deser = (ArrayList<Object>) ois.readObject();
                allBookstock = (ArrayList<BookStock>) deser.get(0);
                allCustomers = (ArrayList<Customer>) deser.get(1);

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        else {
			allBookstock = new ArrayList<>();
			allCustomers = new ArrayList<>();
			return false;
		}

	}
    boolean isResourcesAvailable() {
	    return resourceFile.exists();
    }

    public void saveMasterPassword(Login admin) {
	    ArrayList<String> adminlogin = new ArrayList<>();
	    adminlogin.add(admin.getUserName()); // username at index 0
	    adminlogin.add(admin.getPassword());  //password at index 1
		adminlogin.add(admin.getForgerPasswordText()); //forgetpasswordtext at index 2
	    try (FileOutputStream fileout = new FileOutputStream(masterPswdFile);
            ObjectOutputStream oos = new ObjectOutputStream(fileout)) {
	        oos.writeObject(adminlogin);
        }
        catch (IOException e) {
	        System.out.println("Error while saving admin login");
        }
    }

    public Login loadMasterPassword() {
	    ArrayList<String> ps;
	    try (FileInputStream filein = new FileInputStream(masterPswdFile);
             ObjectInputStream ois = new ObjectInputStream(filein)) {
	        ps = (ArrayList<String>) ois.readObject();
	        return new Login(ps.get(0),ps.get(1),ps.get(2));
        }
        catch (IOException | ClassNotFoundException e) {

        }
        return null;
    }
	public void run() {
	    saveResources();
    }
	
}
