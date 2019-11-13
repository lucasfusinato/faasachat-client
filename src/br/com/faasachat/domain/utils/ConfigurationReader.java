package br.com.faasachat.domain.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import br.com.faasachat.domain.adapter.GsonAdapter;

/**
 * Generic JSON configuration reader from file class.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 13/11/2019
 * @version 1.0
 */
public class ConfigurationReader {
    
    /**
     * Path to read configuration.
     */
    private final String path;
    
    /**
     * Instantiates configuration reader.
     * @param path
     */
    public ConfigurationReader(String path) {
        this.path = path;
    }
    
    /**
     * Reads configuration in path.
     * @return
     */
    public Configuration read() {
        try {
            String content = Files.readString(Paths.get(path));
            return GsonAdapter.getInstance().fromJson(content, Configuration.class);
        } catch (IOException e) {
            return new Configuration();
        }
    }

}
