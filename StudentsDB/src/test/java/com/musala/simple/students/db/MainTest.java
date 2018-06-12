package com.musala.simple.students.db;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.musala.simple.students.db.exceptions.SQLConnectionException;
import com.musala.simple.students.db.exceptions.StudentDataParsingException;

public class MainTest {

	final static Logger LOGGER = LoggerFactory.getLogger(MainTest.class);

	private ClassLoader classLoader = new Main().getClass().getClassLoader();
	private File file = new File(classLoader.getResource("myData.json").getFile());
	private final String testPath = file.getAbsolutePath();

	@Test
	public void testPassingOneArgument() {
		String[] args = { testPath };
		try {
			Main.main(args);
			assertTrue(true);
		} catch (StudentDataParsingException | SQLConnectionException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testPassingTwoArguments() {
		String[] args = { testPath, "2" };
		try {
			Main.main(args);
			assertTrue(true);
		} catch (StudentDataParsingException | SQLConnectionException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testPassingThreeArguments() {
		String[] args = { testPath, "2", "both" };
		try {
			Main.main(args);
			assertTrue(true);
		} catch (StudentDataParsingException | SQLConnectionException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test
	public void testPassingFourArguments() {
		String[] args = { testPath, "2", "both", "fourthArgument" };
		try {
			Main.main(args);
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
			e.printStackTrace();
		} catch (StudentDataParsingException | SQLConnectionException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test
	public void testPassingWrongPath() {
		String[] args = { "wrong path" };
		try {
			Main.main(args);
			assertTrue(false);
		} catch (StudentDataParsingException e) {
			if (e.getCause().getClass() == FileNotFoundException.class) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
			e.printStackTrace();
		} catch (SQLConnectionException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test
	public void testPassingMongoAsDB() {
		String[] args = { testPath, "2", "MONGO" };
		try {
			Main.main(args);
			assertTrue(true);
		} catch (StudentDataParsingException | SQLConnectionException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test
	public void testPassingMySQLAsDB() {
		String[] args = { testPath, "2", "MYSQL" };
		try {
			Main.main(args);
			assertTrue(true);
		} catch (StudentDataParsingException | SQLConnectionException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test
	public void testPassingInvalidId() {
		String[] args = { testPath, "999" };
		try {
			Main.main(args);
			assert (Main.LOGGER.isInfoEnabled() && Main.LOGGER.isWarnEnabled());
		} catch (StudentDataParsingException | SQLConnectionException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test
	public void testPassingInvalidDB() {
		String[] args = { testPath, "3", "INVALID" };
		try {
			Main.main(args);
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
			e.printStackTrace();
		} catch (StudentDataParsingException | SQLConnectionException e) {
			assertTrue(false);
			e.printStackTrace();
		}

	}

}
