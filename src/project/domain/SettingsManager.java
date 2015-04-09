package project.domain;

public interface SettingsManager {

	public boolean getBoolProperty(String key);

	public int getIntegerProperty(String key);

	public String getProperty(String key);

	public void restoreDefault();

	public void setProperty(String key, boolean value);

	public void setProperty(String key, int value);

	public void setProperty(String key, String value);

}
