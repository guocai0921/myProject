package com.guocai.common.utils;

import com.itextpdf.text.pdf.codec.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.Date;

/**
 * 界面js canvas图形导出为图片
 * @author snoopy-li
 *
 */
public class Canvas2ImageServlet extends HttpServlet {
	private static final long serialVersionUID = -3236879832509361392L;
	private static Log logger = LogFactory.getLog(Canvas2ImageServlet.class);
    public void init() throws ServletException {
		
    }
    public void service(javax.servlet.http.HttpServletRequest request,
                        javax.servlet.http.HttpServletResponse response) throws
            javax.servlet.ServletException, java.io.IOException {
    	
    	String canvasString = request.getParameter("data");
        String type = request.getParameter("type");
        
        if(canvasString ==null || type==null) {
        	response.getOutputStream().write("缺少必要的参数，请检查.".getBytes());
        	response.getOutputStream().flush();
            response.getOutputStream().close();
            return;
        }
        
		byte[] canvasByte = Base64.decode(canvasString);
        response.setContentType(type);
        String filename = DateUtil.formatDisplayDate(new Date(), "yyyyMMddHHmmss") + "." + type.substring(6);
        response.setHeader("Content-disposition","attachment;filename=" + filename);

        try {
        	response.getOutputStream().write(canvasByte);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }catch (Exception e){
            response.getOutputStream().close();
            e.printStackTrace();
        }
    }
}
