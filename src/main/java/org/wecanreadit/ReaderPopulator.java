package org.wecanreadit;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReaderPopulator implements CommandLineRunner {

	@Resource
	private ReaderRepository readerRepo;

	@Resource
	private GroupRepository groupRepo;

	@Resource
	private GoalRepository goalRepo;

	@Resource
	private DiscussionQuestionRepository questRepo;

	@Resource
	private DiscussionAnswerRepository ansRepo;

	@Resource
	private GroupBookRepository bookRepo;

	@Resource
	private ReaderProgressRecordRepository readerBookRepo;

	@Resource
	private LibrarianRepository librarianRepo;

	@Override
	public void run(String... args) throws Exception {
		Reader shane = new Reader("Shane", "Em", "Shane", "Em");
		Reader zack = new Reader("Zack", "Mike", "Zack", "Am");
		Reader bob = new Reader("Bob", "Em", "Bob", "Em");
		Reader joe = new Reader("Joe", "Mike", "Joe", "Am");
		Reader vi = new Reader("Vi", "Em", "Vi", "Em");
		Reader doug = new Reader("Doug", "Mike", "Doug", "Am");
		shane = readerRepo.save(shane);
		zack = readerRepo.save(zack);
		bob = readerRepo.save(bob);
		joe = readerRepo.save(joe);
		vi = readerRepo.save(vi);
		doug = readerRepo.save(doug);

		ReadingGroup test1 = new ReadingGroup("Group 1", "Everything", shane, zack, joe);
		ReadingGroup test2 = new ReadingGroup("Group 2", "Everything", vi, doug, joe, shane);
		ReadingGroup test3 = new ReadingGroup("Group 3", "Everything", zack, bob, vi, doug);

		test1 = groupRepo.save(test1);
		test2 = groupRepo.save(test2);
		test3 = groupRepo.save(test3);

		GroupBook book1 = bookRepo.save(new GroupBook("The Lightning Thief", "Rick Riordan", test1));
		GroupBook book2 = bookRepo.save(new GroupBook("Inkheart", "Cornelia Funke", test1));
		GroupBook book3 = bookRepo.save(new GroupBook("Harry Potter and the Sorcerer's Stone", "JK Rowling", test2));
		GroupBook book4 = bookRepo.save(new GroupBook("The Hobbit", "J.R.R. Tolkein", test3));

		GroupBook book5 = bookRepo.save(new GroupBook("The Hunger Games", "Suzanne Collins", test1));
		GroupBook book6 = bookRepo.save(new GroupBook("A Wrinkle in Time", "Madeleine L'Engle", test2));
		GroupBook book7 = bookRepo.save(new GroupBook("The Giver", "Lois Lowry", test2));
		GroupBook book8 = bookRepo.save(new GroupBook("Ender's Game", "Orson Scott Card", test3));

		ReaderProgressRecord joeProgressRecord1 = readerBookRepo.save(new ReaderProgressRecord(book1, joe, 2, 2, 2018));
		ReaderProgressRecord joeProgressRecord2 = readerBookRepo
				.save(new ReaderProgressRecord(book5, joe, 5, 25, 2018));
		ReaderProgressRecord joeProgressRecord3 = readerBookRepo
				.save(new ReaderProgressRecord(book2, joe, 10, 18, 2018));
		ReaderProgressRecord viProgressRecord1 = readerBookRepo.save(new ReaderProgressRecord(book8, vi, 9, 23, 2018));
		ReaderProgressRecord dougProgressRecord1 = readerBookRepo
				.save(new ReaderProgressRecord(book8, doug, 8, 14, 2018));

		joe.getPendingFriends().add(shane);
		zack.getPendingFriends().add(joe);
		vi.getPendingFriends().add(joe);
		vi.getPendingFriends().add(shane);
		shane.getFriends().add(doug);
		doug.getFriends().add(shane);
		joe = readerRepo.save(joe);
		vi = readerRepo.save(vi);
		zack = readerRepo.save(zack);
		shane = readerRepo.save(shane);
		doug = readerRepo.save(doug);

		Librarian mike = new Librarian("Mike", "Myers", "Beachwood", "waynesworld.com", "Mike", "password",
				"action");
		Librarian angie = new Librarian("Angie", "Smith", "Heights", "smithtown.com", "Angie", "password",
				"biography");

		Librarian ashley = new Librarian("Ashley", "Stanley", "South Euclid-Lyndhurst", "sheslays.com", "ash321",
				"password91", "science fiction");

		Librarian testLib = new Librarian("Test", "Test", "Test", "Test", "Test", "Test", "Test");

		testLib = librarianRepo.save(testLib);
		mike = librarianRepo.save(mike);
		angie = librarianRepo.save(angie);
		ashley = librarianRepo.save(ashley);
		
		doug.setLibrarian(angie);
		zack.setLibrarian(angie);
		vi.setLibrarian(angie);
		joe.setLibrarian(angie);
		shane.setLibrarian(angie);
		test1.setLibrarian(angie);
		test2.setLibrarian(angie);
		shane = readerRepo.save(shane);
		doug = readerRepo.save(doug);
		zack = readerRepo.save(zack);
		joe = readerRepo.save(joe);
		vi = readerRepo.save(vi);
		test1 = groupRepo.save(test1);
		test2 = groupRepo.save(test2);
		

	}

}
