package model;

import java.io.Serializable;

/**
 * Represents an administrator that uses the MOBLIMA application.
 * An administrator can log in to the admin module to configure various system settings and update movie information.
 */

public class Staff implements Serializable {

	/**
	 * The username of the admin account to log-in to the admin module.
	 */
    private String username;
    
    /**
     * the password of the admin account to log-in to the admin module.
     */
    private String password;
    
    /**
     * Creates a new administrator account with the given username and password
     * which will be used to log-in to the admin module.
     * @param username this admin's username.
     * @param password this admin's password.
     */
    public Staff(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username of this administrator.
     * @return this admin's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of this administrator.
     * @return this admin's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Checks if this adminUser object is equal to the adminUser object passed in.
     * Confirms if the credentials entered by a user is correct.
     * @param adminUser
     * @return True if username and password entered corresponds to a valid admin account in the database.
     */
    public boolean equals(Staff adminUser){
        return (this.username.equals(adminUser.getUsername()) && this.password.equals(adminUser.getPassword()));
    }
}