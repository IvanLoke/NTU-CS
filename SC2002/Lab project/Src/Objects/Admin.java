package Objects;

import java.io.Serializable;

/** 
 * Represents an Admin user account.
 * @author Chay Hui Xiang
 * @version 1.0
 * @since 2022-11-11
 */
public class Admin implements Serializable {

    /**
     * The username of this Admin account.
     */
    private String username;

    /**
     * The password of this Admin account.
     */
    private String password;

    /**
     * Creates a new Admin account with the specified username and password.
     * @param username the username of this new Admin account.
     * @param password the password of this new Admin account.
     */
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieves the username of this Admin account.
     * @return username of this Admin account.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password of this Admin account.
     * @return password of this Admin account.
     */
    public String getPassword() {
        return password;
    }
}
