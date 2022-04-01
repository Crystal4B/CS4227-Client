package hotelsystem;

import java.io.InputStream;
import java.util.Properties;

/**
 * Config class to load config.properties into the application
 * @author Marcin Sęk
 */
public class Config
{
	// list of available properties
	public static final String SERVER_IP = "server-ip";

	// class variables
	private static Properties properties = initProperties();

	/**
	 * Function initialising the properties of the project
	 * @return the Properties after they are read and loaded
	 */
	private static Properties initProperties()
	{
		try
		{
			properties = new Properties();
			String propFileName = "config.properties";

			InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(propFileName);
			properties.load(inputStream);
			inputStream.close();

			return properties;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		return null;
	}

	/**
	 * Function for retrieving a property from the config
	 * @param propertyName name of the property being retrieved
	 * @return The property as a String
	 */
	public static String getProperty(String propertyName)
	{
		return properties.getProperty(propertyName);
	}
}
