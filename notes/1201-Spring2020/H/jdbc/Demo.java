package unl.cse.film;

import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

public class Demo {

	public static void main(String args[]) {
			    
		Configurator.initialize(new DefaultConfiguration());
		Configurator.setRootLevel(Level.INFO);

		List<Film> films = Film.loadAllFilms();
		for(Film f : films) {
			System.out.println(f);
		}
		
		Director.addNewDirector("Kevin", "Smith");
		
	}

}
