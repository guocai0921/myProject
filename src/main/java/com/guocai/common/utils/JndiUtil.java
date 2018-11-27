package com.guocai.common.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.EnvironmentConfiguration;
import org.apache.commons.configuration.MapConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.*;

@SuppressWarnings("unchecked")
public class JndiUtil {

	private static final  Logger logger = LoggerFactory.getLogger(JndiUtil.class);

	private static final String configFile="jndi.properties";
	/*
	 * this is for default configuration
	 */
	private static final Configuration JNDI_CONFIG=JndiUtil.getConfiguration(configFile);
	
	private static final String JNDI_PROVIDER_URL = "java.naming.provider.url";
	private static final String JNDI_INITIAL_CONTEXT_FACTORY = "java.naming.factory.initial";
	private static final String JNDI_FACTORY_URL_PKGS = "java.naming.factory.url.pkgs";
	private static final String JNDI_USER = "java.naming.security.principal";
	private static final String JNDI_PASSWORD = "java.naming.security.credentials";
	private static final String JNDI_SECURITY_AUTH="";
	private static final String PREFIX="jndi.param";
	private static final String SEPARATOR=":";
		
	/**
	 * performs a safe close of the JNDI Context with no Exception. / public
	 * static void close(Context context) { try { context.close(); } catch
	 * (Throwable ignored) {} }
	 * 
	 * 
	 * this function will build an initial set of Hashtable properties for
	 * creating an initial context. It will use the following properties if they
	 * exist:<b/>
	 * <ul>
	 * <li>JNDI_PROVIDER_URL - required</li>
	 * <li>JNDI_INITIAL_CONTEXT_FACTORY - required</li>
	 * <li>JNDI_FACTORY_URL_PKGS - optional</li>
	 * <li>JNDI_USER - optional</li>
	 * <li>JNDI_PASSWORD - optional</li>
	 * <li>jndi.param.(property name) - optional </li>
	 * </ul>
	 *
	 */
	@SuppressWarnings("unchecked")
	private static final Hashtable<String, String> getInitialContextEnv(Configuration config) {
		long start=System.currentTimeMillis();
		
		String jndiUrl = config.getString(JNDI_PROVIDER_URL);
		String jndiFactory = config.getString(JNDI_INITIAL_CONTEXT_FACTORY);
		String jndiPkgs = System.getProperty(JNDI_FACTORY_URL_PKGS, null);
		String jndiUser = config.getString(JNDI_USER, null);
		String jndiPassword = config.getString(JNDI_PASSWORD, null);
		String auth=config.getString(JNDI_SECURITY_AUTH);
		
		Hashtable<String, String> props = new Hashtable<String, String>();

		// insert extra properties that are unknown to jndi.param
		Configuration subConfig=config.subset(PREFIX);
		Iterator <String> keys = subConfig.getKeys();
		while (keys.hasNext()) {
			String token =keys.next();			
			props.put(token, subConfig.getString(token));
		}
		
		if(StringUtils.isNotBlank(jndiUrl)) {
			props.put(Context.PROVIDER_URL, jndiUrl);
		}
		if(StringUtils.isNotBlank(jndiFactory)) {
			props.put(Context.INITIAL_CONTEXT_FACTORY, jndiFactory);
		}
		
		if (StringUtils.isNotBlank(jndiPkgs)) {
			props.put(Context.URL_PKG_PREFIXES, jndiPkgs);
		}
		if (StringUtils.isNotBlank(jndiUser)) {
			props.put(Context.SECURITY_PRINCIPAL, jndiUser);
		}
		if (StringUtils.isNotBlank(jndiPassword)) {
			props.put(Context.SECURITY_CREDENTIALS, jndiPassword);
		}		
		if(StringUtils.isNotBlank(auth)) {
			props.put(Context.SECURITY_AUTHENTICATION, auth);
		}
		logger.info("initialize context duration=" + (System.currentTimeMillis()- start) + " mills" );
		
		if(logger.isDebugEnabled()) {
			logger.debug("context properties: " + props);
		}
		
		return props;
	}

	/**
	 * This function will build an InitialContext from properties located within
	 * the global configuration. It will use the following properties if they
	 * exist:<b/> <p/>
	 * the default configuration file is: jndi.properties, which is located in classpath.
	 * @throws NamingException on external naming errors
	 */
	public static final Context getInitialContext() throws NamingException {
		Hashtable<String, String> props = getInitialContextEnv(JNDI_CONFIG);
		return new InitialContext(props);
	}

	/**
	 * This function will return an initial context based on the properties
	 * found in getInitialContextEnv() and the ones supplied in the extraProps
	 * variable. <p/>
	 * 
	 * @param extraProps user-supplied properties to be added to standard set
	 * @throws ConfigException  if missing required config item
	 * @throws NamingException  on external naming errors
	 */
	public static final Context getInitialContext(Map<String, String> extraProps) throws  NamingException {
		Hashtable<String, String> props = getInitialContextEnv(JNDI_CONFIG);
		props.putAll(extraProps);

		return new InitialContext(props);
	}
	
	/**
	 * returns an initial Context based on the properties defined in the specified jndiFileName.
	 * @param jndiFileName a property file which holds the properties to initialize the Context
	 * @return an initial Context
	 * @throws NamingException thrown if failed to initialize the Context
	 */
	public static final Context getInitialContext(String jndiFileName) throws NamingException {
		Configuration config=JndiUtil.getConfiguration(jndiFileName);
		
		Hashtable<String, String> props = getInitialContextEnv(config);
		return new InitialContext(props);
	}
	
	/**
	 * returns an initial Context Map based on the properties defined in the specified jndiFileName.
	 * it uses the prefix defined in the key server.list as the key in the Map, while the value is the Context.<br>
	 * this method supports to define multiple Context in the specified jndiFileName. <br>
	 * for the default Context, key equals to "" (empty String).
	 * @param config a Configuration holds the properties to initialize the Context
	 * @return a Context Map, key is prefix defined in server.list, value is Context
	 * @throws NamingException
	 * @author sunf
	 * @since  2008-10-16
	 */
	public static Map<String, Context> getInitialContextMap(Configuration config) throws NamingException {
		Map<String, Context> contextMap=new HashMap<String, Context>(5);
		String [] serverList=getPrefix(config);
		Hashtable<String, String> sharedProps=getSharedProperties(config, serverList);		
		Context context=getDefaultContext(sharedProps);
		if(context !=null) {
			contextMap.put("", context);
		}		
		for(int i=0; i<serverList.length; i++) {
			String prefix=serverList[i];
			Configuration subConfig=config.subset(prefix);
			if(isContextEnv(subConfig)) {
				Hashtable<String, String> props =getInitialContextEnv(subConfig);
				props.putAll(sharedProps);
				Context subContext=null;;
                try {
                    subContext = new InitialContext(props);
                    contextMap.put(prefix, subContext);
                    if(logger.isDebugEnabled()) {
                        logger.debug("context: " + prefix + ": " + context);
                    }
                } catch (Exception ex) {
                    logger.warn("failed to initialize context " + prefix, ex);
                }				
			}
		}
		
		return contextMap;
	}
	/**
	 * returns an initial Context Map based on the properties defined in the specified jndiFileName.
	 * it uses the prefix defined in the key server.list as the key in the Map, while the value is the Context.<br>
	 * this method supports to define multiple Context in the specified jndiFileName. <br>
	 * for the default Context, key equals to "" (empty String).
	 * @param jndiFileName a property file which holds the properties to initialize the multiple Context
	 * @return a Map holds the key and Value Context
	 * @throws NamingException thrown if failed to initialize the Context
	 * @author sunf
	 * @since  2008-10-16
	 */
	public static final Map<String, Context> getInitialContextMap(String jndiFileName) throws NamingException {
		Configuration config=getConfiguration(jndiFileName);
		
		Map<String, Context> contextMap=getInitialContextMap(config);
		return contextMap;
	}
	
	private static String [] getPrefix(Configuration config) {		
		String [] serverList=config.getStringArray("prefix.list");	
		if(serverList.length==1 && StringUtils.isBlank(serverList[0])) {
		    serverList=new String[0];
		}
		logger.debug("prefix list: " + Arrays.asList(serverList));
		return serverList;
	}
	
	private static Hashtable<String, String> getSharedProperties(Configuration sharedConfig, String[] excludedPrefixs) {
		Hashtable<String, String> sharedProps=new Hashtable<String, String>();
		Iterator<String> keys=sharedConfig.getKeys();
		int excludedPrefixLen=excludedPrefixs.length;
		while(keys.hasNext()) {
			String key=keys.next();
			if(excludedPrefixLen>0) {
				for(int i=0; i<excludedPrefixLen; i++) {
					String prefix=excludedPrefixs[i];
					boolean isShared=! key.startsWith(prefix);
					if(isShared) {
						sharedProps.put(key, sharedConfig.getString(key));
					}
				}
			} else {
				sharedProps.put(key, sharedConfig.getString(key));
			}

		}
		logger.debug("shared properties: " + sharedProps);
		return sharedProps;
	}
	
	private static boolean isContextEnv(Configuration config) {		
		return config.containsKey(JNDI_PROVIDER_URL) 
			&& config.containsKey(JNDI_INITIAL_CONTEXT_FACTORY);
	}
	
	private  static Context getDefaultContext(Hashtable<String, String> sharedProps) throws NamingException {		
		Configuration config=new MapConfiguration(sharedProps);	
		
		Context context=null;
		if(isContextEnv(config) ) {
			Hashtable props=getInitialContextEnv(config);
			try {
                context=new InitialContext(props);
            } catch (Exception ex) {
                logger.warn("failed to initialize the default context because of " + ex.getMessage());
            }
		} 
		//removes the properties defined for default context
		sharedProps.remove(JNDI_PROVIDER_URL);
		sharedProps.remove(JNDI_INITIAL_CONTEXT_FACTORY);
		sharedProps.remove(JNDI_FACTORY_URL_PKGS);
		sharedProps.remove(JNDI_USER);
		sharedProps.remove(JNDI_PASSWORD);
		
		Iterator<String> defaultKeyIter=sharedProps.keySet().iterator();
		while(defaultKeyIter.hasNext()) {
			String key=defaultKeyIter.next();
			if(key.startsWith(PREFIX)) {
				defaultKeyIter.remove();				
			}
		}
		
		logger.debug("non-default shared properties: " + sharedProps);
		logger.debug("default context: " + context);
		return context;
	}
	
	public static  Configuration getConfiguration(String fileName) {
		long start=System.currentTimeMillis();
		Configuration config=null;
		try{
			config=new PropertiesConfiguration(fileName);
			
		}	catch (Throwable ex) {
			config=new EnvironmentConfiguration();
			logger.error("failed to initialize this file." + fileName, ex);
		}
		if(logger.isDebugEnabled()) {
			logger.debug("configured properties defined in file " + fileName + " listed as below: " );
			for(Iterator<String> iter=config.getKeys(); iter.hasNext(); ) {
				String key=iter.next();
				logger.debug("key=" + key + ", value=" + config.getString(key));
			}
			logger.debug("end of configured properties.");
		}
		logger.info("initialize configuration file " + fileName +" duration=" + (System.currentTimeMillis()-start) + " mills");
		return config;
	}
	
	public static Object locate(Context ctx, String jndiName) throws NamingException {
		if(logger.isDebugEnabled()) {
			logger.debug("trying to locate the jndi object: " + jndiName + " on context " + ctx.getEnvironment().get(Context.PROVIDER_URL) + " ... ");
		}
		Object obj = null;
		
		try {
			obj = ctx.lookup(jndiName);
			
		} catch (NamingException n1) {
			// java:comp/env/ObjectName to ObjectName
			logger.warn(n1.getMessage() );
			if (jndiName.startsWith("java:comp/env/")) {
				try {
					obj = ctx.lookup(StringUtils.replace(jndiName, "java:comp/env/", ""));
				} catch (NamingException n2) {
					// java:comp/env/ObjectName to java:ObjectName
					logger.warn(n2.getMessage() + " not found. ");
					obj = ctx.lookup(StringUtils.replace(jndiName, "comp/env/", ""));
				}
			} else if (jndiName.startsWith("java:")) { // java:ObjectName to ObjectName
				try {
					obj = ctx.lookup(StringUtils.replace(jndiName, "java:", ""));
				} catch (NamingException n2) {
					// java:ObjectName to java:comp/env/ObjectName
					logger.warn( n2.getMessage() + " not found. ");
					obj = ctx.lookup(StringUtils.replace(jndiName, "java:", "java:comp/env/"));
				}
			} else if (! jndiName.startsWith("java:") ) { // ObjectName to java:ObjectName
				try {
					obj = ctx.lookup("java:" + jndiName);
				} catch (NamingException n2) {
					logger.warn(n2.getMessage()+ " not found.");

					try {
						obj = ctx.lookup("java:comp/" + jndiName);
					} catch (NamingException n3) {				
						logger.warn(n3.getMessage() + " not found. ");
						try {
							obj = ctx.lookup("java:comp/env/" + jndiName);
						} catch (NamingException n4) {
							logger.warn(n4.getMessage() + " not found. ");
							throw new NamingException("Jndi Name: " + jndiName + " not found.");
						}	
					}
				}
			} else {
				logger.error("Exception: Jndi Name: " + jndiName + " not found.");
				throw new NamingException("Jndi Name: " + jndiName + " not found." );
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage() + " not found.", ex);
			throw new NamingException("Jndi Name: " + ex.getMessage() + " not found." );
		}

		return obj;		
	}
	/**
	 * returns the specified key's value which is defined in the jndi.properties.
	 * @param key the property key
	 * @return value of this key, if no value found, returns null
	 */
	public static String getEnvironment(String key) {
		if(StringUtils.contains(key, SEPARATOR)) {
			return JNDI_CONFIG.subset(StringUtils.substringBefore(key, SEPARATOR))
				.getString(StringUtils.substringAfter(key, SEPARATOR));
		} else {
			return JNDI_CONFIG.getString(key);
		}		
	}
	
}
