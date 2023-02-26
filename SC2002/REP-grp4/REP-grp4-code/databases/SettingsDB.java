package databases;

import objects.Settings;

/**
 * Represents the concrete DB class for Settings.
 * 
 * @author Aaron Chua
 * @version 1.0
 * @since 2022-11-11
 */
public class SettingsDB extends SerializeDB<Settings> {
    /**
     * Creates a new SettingsDB with the given filename.
     */
    public SettingsDB() {
        this.filename = "databases/settings.dat";
    }
}
