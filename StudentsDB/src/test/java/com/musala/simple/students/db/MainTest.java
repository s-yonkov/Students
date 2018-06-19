package com.musala.simple.students.db;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.musala.simple.students.db.exceptions.SQLConnectionException;
import com.musala.simple.students.db.exceptions.StudentDataParsingException;

public class MainTest {

    class MainForTest extends Main {
    }

    final static Logger LOGGER = LoggerFactory.getLogger(MainTest.class);

    private ClassLoader classLoader = new Main().getClass().getClassLoader();
    private File file = new File(classLoader.getResource("myData.json").getFile());
    private final String testPath = file.getAbsolutePath();
    StudentGroup studentGroup;

    @Before
    public void initStudentGroup() {
        studentGroup = new StudentGroup();
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(1, "Ivan", 31, 5));
        studentGroup.setStudents(students);
    }

    @Test
    public void testFileExists() {
        // Passing existing file
        assertTrue(MainForTest.fileExists(testPath));
        // Passing invalid path
        assertFalse(MainForTest.fileExists("This path is invalid"));
    }

    @Test
    public void testPrintFromBkp() throws StudentDataParsingException, SQLConnectionException {
        MainForTest testMain = new MainForTest();
        MainForTest mockMain = mock(MainForTest.class);

        testMain.printFromBkp(studentGroup);
        verify(mockMain).printAllElements(studentGroup);
    }

    @Test
    public void testIsValidId() {
        String number = "5";
        String text = "five";
        assertTrue(MainForTest.isValidId(number));
        assertFalse(MainForTest.isValidId(text));
    }

    /*
     * @Test
     * public void testPassingOneArgument() throws StudentDataParsingException, SQLConnectionException {
     * String[] args = { testPath };
     * 
     * Main mainClass = mock(Main.class);
     * verify(mainClass).main(args);
     * }
     * 
     * @Test
     * public void testPassingTwoArguments() {
     * String[] args = { testPath, "2" };
     * try {
     * Main.main(args);
     * assertTrue(true);
     * } catch (StudentDataParsingException | SQLConnectionException e) {
     * assertTrue(false);
     * }
     * }
     * 
     * @Test
     * public void testPassingThreeArguments() {
     * String[] args = { testPath, "2", "both" };
     * try {
     * Main.main(args);
     * assertTrue(true);
     * } catch (StudentDataParsingException | SQLConnectionException e) {
     * assertTrue(false);
     * }
     * }
     * 
     * @Test
     * public void testPassingFourArguments() {
     * String[] args = { testPath, "2", "both", "fourthArgument" };
     * try {
     * Main.main(args);
     * assertTrue(false);
     * } catch (IllegalArgumentException e) {
     * assertTrue(true);
     * } catch (StudentDataParsingException | SQLConnectionException e) {
     * assertTrue(false);
     * }
     * }
     * 
     * @Test
     * public void testPassingWrongPath() {
     * String[] args = { "wrong path" };
     * try {
     * Main.main(args);
     * assertTrue(false);
     * } catch (StudentDataParsingException e) {
     * if (e.getCause().getClass() == FileNotFoundException.class) {
     * assertTrue(true);
     * } else {
     * assertTrue(false);
     * }
     * } catch (SQLConnectionException e) {
     * assertTrue(false);
     * }
     * }
     * 
     * @Test
     * public void testPassingMongoAsDB() {
     * String[] args = { testPath, "2", "MONGO" };
     * try {
     * Main.main(args);
     * assertTrue(true);
     * } catch (StudentDataParsingException | SQLConnectionException e) {
     * assertTrue(false);
     * }
     * }
     * 
     * @Test
     * public void testPassingMySQLAsDB() {
     * String[] args = { testPath, "2", "MYSQL" };
     * try {
     * Main.main(args);
     * assertTrue(true);
     * } catch (StudentDataParsingException | SQLConnectionException e) {
     * assertTrue(false);
     * }
     * }
     * 
     * @Test
     * public void testPassingInvalidId() {
     * String[] args = { testPath, "999" };
     * try {
     * Main.main(args);
     * assert (Main.LOGGER.isInfoEnabled() && Main.LOGGER.isWarnEnabled());
     * } catch (StudentDataParsingException | SQLConnectionException e) {
     * assertTrue(false);
     * }
     * }
     * 
     * @Test
     * public void testPassingInvalidDB() {
     * String[] args = { testPath, "3", "INVALID" };
     * try {
     * Main.main(args);
     * assertTrue(false);
     * } catch (IllegalArgumentException e) {
     * assertTrue(true);
     * } catch (StudentDataParsingException | SQLConnectionException e) {
     * assertTrue(false);
     * }
     */
}
