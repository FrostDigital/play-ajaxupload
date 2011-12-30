package controllers;

import java.io.File;

import play.Logger;
import play.Play;
import play.mvc.Controller;
import play.mvc.Http;

public class Application extends Controller {
	
	public static void example1() {
		Logger.warn(params.all().toString());
        render();
    }
	
	public static void example2() {
		Logger.warn(params.all().toString());
        render();
    }
	
	
	public static void upload(File qqfile) {
		Logger.info("Uploaded file is temporarily saved here; %s", qqfile.getAbsolutePath());
		renderHtml("{success:true}");
    }
	

}