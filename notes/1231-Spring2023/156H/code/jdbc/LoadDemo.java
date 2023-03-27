package unl.soc.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

public class LoadDemo {

	public static final Random r = new Random();
	public static LocalDate today = LocalDate.of(2023, 3, 26);
	private static final Logger LOGGER = LogManager.getLogger(LoadDemo.class);
	private static final int NUM_WORKERS = 10;

	public static Person randomPerson() {
		return new Person(String.valueOf(r.nextInt()), String.valueOf(r.nextInt()), today);

	}
	
	public static void parallel() {
		
		ExecutorService pool = Executors.newFixedThreadPool(NUM_WORKERS);

		ConnectionPool.initialize();
		
		List<LoadWorker> workers = new ArrayList<>();
		for(int i=0; i<NUM_WORKERS; i++) {
			workers.add(new LoadWorker(i+1));
		}
		
		long start = System.currentTimeMillis();
		for (LoadWorker worker : workers) {
			pool.submit(worker);
		}
		try {
			//signal to shutdown when all running tasks have completed
			pool.shutdown();
			//and await its shutdown with a time out
			pool.awaitTermination(1, TimeUnit.HOURS);
			long end = System.currentTimeMillis();
			System.out.println("Time: " + (end - start) / 1000.0);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			pool.shutdown();
		}
		
	}
	
	public static void serial() {

		//off campus: 10 = 2.89s, 100 = 24.85s
		//on: 100 = 2.4s, 1000 = 20.66s
		int n = 1000;
		long start = System.currentTimeMillis();
		for(int i=0; i<n; i++) {
			Person p = randomPerson();
			DataSaver.savePerson(p);
		}
		long end = System.currentTimeMillis();
		double time = (end - start) / 1000.0;
		System.out.printf("Done: %.2f seconds\n", time);
		
	}

	public static void main(String args[]) {
		Configurator.initialize(new DefaultConfiguration());
		Configurator.setRootLevel(Level.INFO);
		LOGGER.info("Started...");
		//serial();
		parallel();
	}
}
