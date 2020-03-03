package ie.ucd.DoHO.model;

import javax.persistence.Entity;

@Entity
public class Comic extends Magazine {
	public Comic() {
	}

	public Comic(String title, String author, String publisher, Date releaseDate, String genre,
				 String libraryLocation, String language, String frequency, int issue) {
		super(title, author, publisher, releaseDate, genre, libraryLocation, language, frequency, issue);
		setSubject("Comic");
	}
}
