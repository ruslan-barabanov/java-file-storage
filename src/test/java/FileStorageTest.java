import exception.FileNameAlreadyExistsException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FileStorageTest {

    @Test
    public void testWriteReturnsTrue() throws FileNameAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        File file = new File("Princess", "Jack");
        assertTrue(fileStorage.write(file));
    }

    //    @Test
//    public void testWriteThrowsException() {
//        FileStorage fileStorage = new FileStorage();
//        fileStorage.write();
//    }
//    @Test
//    public void testWriteReturnsFalse() throws FileNameAlreadyExistsException {
//        FileStorage fileStorage = new FileStorage(12);
//        File file2 = new File("Princess", "Max");
//        assertFalse(fileStorage.write());
//    }
    @Test
    public void testIsExist() throws FileNameAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        fileStorage.write(new File("Django", "Elvis"));
        fileStorage.write(new File("Django", "Elvis"));
    }

    @DataProvider(name = "content-size-data-provider")
    public Object[][] fileNameDP() {
        return new Object[][]{{1000, "12345"}, {2, "12312313123"}};
    }

    @Test(expectedExceptions = FileNameAlreadyExistsException.class)
    public void testDuplicatedFilename() throws FileNameAlreadyExistsException {
        // don't care about file storage name here
        FileStorage fs = new FileStorage(1000);
        fs.write(new File("filename", "something"));
        fs.write(new File("filename", "something"));
    }

    @Test
    public void testOversizeAssertTrue() throws FileNameAlreadyExistsException {
        // don't care about file storage name here
        FileStorage fs = new FileStorage(5);
        assertTrue(fs.write(new File("filename", "12345")));
    }

    @Test
    public void testOversizeAssertFalse() throws FileNameAlreadyExistsException {
        // don't care about file storage name here
        FileStorage fs = new FileStorage(2);
        assertFalse(fs.write(new File("filename", "123450")));
    }

    @Test(enabled = false)
    public void ignoreTest() {
        // just ignore this test
    }

}

