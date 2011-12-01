import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.stream.FileImageInputStream;

import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class ApplicationTest extends FunctionalTest {

    @Test
    public void testUpload_multipart() {
    	Map<String, File> files = new HashMap<String, File>();
    	files.put("qqfile", new File("./test/bill.png"));
        Response response = POST("/upload", Collections.EMPTY_MAP, files);
        
        assertIsOk(response);
        assertContentType("text/html", response);
        assertContentEquals("{success:true}", response);
    }

    @Test
    public void testUpload_octetStream() throws Exception {
    	File file = new File("./test/bill.png");
    	Response response = POST("/upload?qqfile=bill.png", "application/octet-stream", new FileInputStream(file));
    	
    	assertIsOk(response);
    	assertContentType("text/html", response);
    	assertContentEquals("{success:true}", response);
    }
    
}