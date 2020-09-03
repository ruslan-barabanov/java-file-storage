import org.junit.Before;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class FileTest extends Assert {

    @Test
    public void testGetSize() {
        File file = new File("Denis.pmg", "Running");
        assertEquals(file.getSize(), 3.0);
    }

    @Test
    public void testFileNotNull() {
        File file = new File("Denis.pmg", "Running");
        assertEquals(file.getSize(), 3.0);
    }
//    @Test
//    public void testGetFilename() {
//        File file = new File("Denis", "Running");
//        assertTrue(file.getFilename(), "\\.\\.");
//    }

    //    @Test
//    public boolean delete(String filename){
//        return files.remove(getFile(filename));
//    }
    @DataProvider(name = "file-name-data-provider")
    public Object[][] fileNameDP() {
        return new Object[][]{{"Stas"}, {"text"}, {"text.txt"}};
    }

    @DataProvider(name = "file-name-extension-data-provider")
    public Object[][] fileNameExtensionDP() {
        return new Object[][]{{"text", ""}, {"text.png", "png"}, {"text.txt", "txt"}};
    }

    @DataProvider(name = "content-data-provider")
    public Object[][] contentDP() {
        return new Object[][]{{"sometext", 4.0}, {"1234", 2.0}, {"1234567890", 5.0}, {"sometex", 3.5},
                {"", 0.0}};
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testFileContentNullCheck() {
        // we don't care here about filename
        new File("text.txt", null);
    }

    @Test(dataProvider = "content-data-provider")
    public void testFileSize(String content, double size) {
        // we don't care here about filename
        File file = new File("text.txt", content);
        System.out.println(file.getSize());
        assertEquals(file.getSize(), content);
    }
}
