import exception.FileNameAlreadyExistsException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FileStorageTest {

    @DataProvider(name = "content-size-data-provider")
    public Object[][] fileNameDP() {
        return new Object[][]{{1000, "12345"}, {2, "12312313123"}};
    }

    @Test
    public void testWriteReturnsTrue() throws FileNameAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        File file = new File("Princess", "Jack");
        assertTrue(fileStorage.write(file));
    }
    @Test
    public void testIsExist() throws FileNameAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        fileStorage.write(new File("Django", "Elvis"));
        assertTrue(fileStorage.isExists("Django"));
    }

    @Test(expectedExceptions = FileNameAlreadyExistsException.class)
    public void testDuplicatedFilename() throws FileNameAlreadyExistsException {
        FileStorage fs = new FileStorage(1000);
        fs.write(new File("filename", "something"));
        fs.write(new File("filename", "something"));
    }

    @Test
    public void testOversizeAssertTrue() throws FileNameAlreadyExistsException {
        FileStorage fs = new FileStorage(5);
        assertTrue(fs.write(new File("filename", "12345")));
    }

    @Test
    public void testOversizeAssertFalse() throws FileNameAlreadyExistsException {
        FileStorage fs = new FileStorage(2);
        assertTrue(fs.write(new File("filename", "123450")));
        assertTrue(fs.write(new File("filename1", "123450")));
        assertFalse(fs.write(new File("filename2", "123450")));
    }
}
