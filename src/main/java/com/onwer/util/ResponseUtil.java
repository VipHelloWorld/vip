package com.onwer.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {

	public void doResponse(HttpServletRequest request, HttpServletResponse response, Object obj) {
		
		String jsonStr = null;
		try {
			jsonStr = JacksonUtil.toJSon(obj);
			response.setContentType("text/html; charset=utf-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			PrintWriter  out = response.getWriter();
			String cb = request.getParameter("callback");
            if(cb != null){//如果是跨域  
            	StringBuffer sb = new StringBuffer(cb);  
            	sb.append("(");  
            	sb.append(jsonStr.toString()); 
            	sb.append(")");  
            	out.write(sb.toString());  
                out.close();  
            }else{//不跨域的情况  
            	out.write(jsonStr.toString());  
                out.close();  
            }
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
