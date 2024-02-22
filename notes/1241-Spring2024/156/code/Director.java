package unl.soc;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ArrayList;

public class Director extends Person {

	private List<Film> directedFilms;

	public Director(String lastName, String firstName, LocalDate dateOfBirth) {
		super(lastName, firstName, dateOfBirth);
		this.directedFilms = new ArrayList<>();
	}

	public void addFilm(Film f) {
		if (f == null) {
			return;
		}
		this.directedFilms.add(f);
	}

	public List<Film> getFilms() {
		return this.directedFilms;
	}
	

}
