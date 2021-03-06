package org.sagebionetworks.warehouse.workers.config;

import javax.servlet.ServletContextEvent;

import org.sagebionetworks.warehouse.workers.AwsModule;
import org.sagebionetworks.warehouse.workers.WorkersModule;
import org.sagebionetworks.warehouse.workers.db.DatabaseModule;
import org.sagebionetworks.warehouse.workers.servlet.WorkersServletModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Listens to the servlet startup and shutdown and creates the application Guice injector.
 * 
 * @author John
 *
 */
public class ApplicationServletContextListener extends GuiceServletContextListener {
	

	ApplicationMain main;
	
	/**
	 * Create the main application Guice Injector with all of the modules.
	 * @return
	 */
	public static Injector createNewGuiceInjector(){
		return  Guice.createInjector(new WorkersServletModule(), new DatabaseModule(), new WorkersModule(), new AwsModule());
	}

	@Override
	protected Injector getInjector() {
		Injector injector = ApplicationServletContextListener.createNewGuiceInjector();
		main = new ApplicationMain(injector);
		return injector;
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// cleanup happens here.
		if(main != null){
			main.shutdown();
		}
		super.contextDestroyed(servletContextEvent);
	}


	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		super.contextInitialized(servletContextEvent);
		if(main != null){
			main.startup();
		}
	}
	

}
