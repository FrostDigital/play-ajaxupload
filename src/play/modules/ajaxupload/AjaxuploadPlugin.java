package play.modules.ajaxupload;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import play.Logger;
import play.Play;
import play.PlayPlugin;
import play.data.Upload;
import play.mvc.Http;
import play.mvc.Http.Request;

public class AjaxuploadPlugin extends PlayPlugin {
	
	public static final String AJAX_UPLOAD_PARAM = "qqfile"; 
	
//	@Override
//	public void onApplicationReady() {
//	}
	
	@Override
	public void beforeActionInvocation(Method actionMethod) {
		Http.Request request = Http.Request.current();
        String qqFile = request.params.get(AJAX_UPLOAD_PARAM);
        
        if(qqFile != null) {
        	
        	// We only need take special care of uploads of type
        	// application/octet-stream. Otherwise, if we got a regular
        	// multipart request Play is doing just fine by itself
    		
        	if(isOctetStream(request)) {
        		Upload ajaxUpload = new AjaxFileUpload(request.body, qqFile);
    			
    			@SuppressWarnings("unchecked") 
    			List<Upload> uploads = (List<Upload>) Request.current().args.get("__UPLOADS");

    			if (uploads == null) {
                    uploads = new ArrayList<Upload>();
                    Request.current().args.put("__UPLOADS", uploads);
                }
    			
    			uploads.add(ajaxUpload);
    			
    			// update "qqfile" to be able to reuse existing binding for multipart request
    			request.params.put(AJAX_UPLOAD_PARAM, AJAX_UPLOAD_PARAM);
    		}
    		
        }
	}
	
	private static boolean isOctetStream(Http.Request request) {
		return request.contentType.equals("application/octet-stream");
	}
	
}
