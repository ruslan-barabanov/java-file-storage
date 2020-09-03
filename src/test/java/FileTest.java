import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class FileTest extends Assert {

    @DataProvider(name = "file-name-data-provider")
    public Object[][] fileNameDP() {
        return new Object[][]{{"Stas"}, {"text"}, {"text.txt"}};
    }

    @DataProvider(name = "content-data-provider")
    public Object[][] contentDP() {
        return new Object[][]{{"sometext", 4.0}, {"1234", 2.0}, {"1234567890", 5.0}, {"sometex", 3.5}, {"", 0.0}};
    }

    @Test(dataProvider = "file-name-data-provider")
    public void testGetFilename(String fileName) {
        File file = new File(fileName, "content");
        assertEquals(file.getFilename(), fileName);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testFileContentNullCheck() {
        File file = new File("text.txt", null);
        assertEquals(file.getSize(), 0.0);
    }

    @Test(dataProvider = "content-data-provider")
    public void testFileSize(String content, double size) {
        // we don't care here about filename
        File file = new File("text.txt", content);
        assertEquals(file.getSize(), size);
    }
}
