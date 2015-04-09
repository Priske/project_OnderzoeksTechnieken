package project.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsManagerDefault implements SettingsManager {

	private final String applicationName;
	private Properties defaultProperties;
	private final File saveFile;
	private Properties userProperties;

	public SettingsManagerDefault(String applicationName, Properties defaultProperties) {
		this(applicationName);
		this.setDefaultProperties(defaultProperties);
	}

	public SettingsManagerDefault(String applicationName) {
		this.applicationName = applicationName;
		this.saveFile = new File("settings");
	}

	@Override
	public synchronized boolean getBoolProperty(String key) {
		return Boolean.parseBoolean(this.getProperty(key));
	}

	@Override
	public synchronized int getIntegerProperty(String key) {
		return Integer.parseInt(this.getProperty(key));
	}

	@Override
	public synchronized String getProperty(String key) {
		if(key == null || key.isEmpty()) {
			throw new IllegalArgumentException("Parameter 'key' cannot be null or empty.");
		}
		if(this.userProperties == null) {
			this.loadProperties();
		}
		String value = this.userProperties.getProperty(key);
		if(value == null) {
			String defaultValue = this.defaultProperties.getProperty(key);
			if(defaultValue == null) {
				throw new IllegalArgumentException("Invalid key '" + key + "', no values were found.");
			}
			this.setProperty(key, defaultValue);
			value = defaultValue;
		}
		return value;
	}

	@Override
	public synchronized void restoreDefault() {
		this.userProperties = null;
		this.storeProperties();
	}

	@Override
	public synchronized void setProperty(String key, boolean value) {
		this.setProperty(key, Boolean.toString(value));
	}

	@Override
	public synchronized void setProperty(String key, int value) {
		this.setProperty(key, Integer.toString(value));
	}

	@Override
	public synchronized void setProperty(String key, String value) {
		if(key == null || key.isEmpty() || value == null || value.isEmpty()) {
			throw new IllegalArgumentException("Parameters 'key' or 'value' cannot be null or empty.");
		}
		if(this.userProperties == null) {
			this.loadProperties();
		}
		this.userProperties.setProperty(key, value);
		this.storeProperties();
	}

	private synchronized void setDefaultProperties(Properties defaultProperties) {
		if(defaultProperties == null) {
			throw new IllegalArgumentException("Default properties cannot be null.");
		}
		this.defaultProperties = defaultProperties;
	}

	private synchronized void init() {
		this.defaultProperties.entrySet().forEach(k -> {
			this.setProperty(k.getKey().toString(), k.getValue().toString());
		});
	}

	private synchronized void loadProperties() {
		try {
			if(this.saveFile.exists()) {
				this.userProperties = new Properties();
				this.userProperties.load(new FileInputStream(this.saveFile));
			} else {
				this.storeProperties();
			}
		} catch (IOException ex) {
			Logger.getLogger(SettingsManagerDefault.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private synchronized void storeProperties() {
		if(this.userProperties == null) {
			this.userProperties = new Properties();
			this.init();
		}
		try {
			this.userProperties.store(new FileOutputStream(this.saveFile), this.applicationName + " settings");
		} catch (IOException ex) {
			Logger.getLogger(SettingsManagerDefault.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
