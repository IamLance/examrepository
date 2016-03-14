package Servlet;

import Bean.AlchemyConnector;
import java.io.*;
import java.net.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.text.ParseException;


@WebServlet(name = "IServlet", urlPatterns = {"/IServlet"})
public class IServlet extends HttpServlet {

	private String FACE_ENDPOINT_URL = "http://gateway-a.watsonplatform.net/calls/url/URLGetRankedImageFaceTags";

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	
		AlchemyConnector connector = new AlchemyConnector();
		//AlchemyVision service = new AlchemyVision();
		//service.setApiKey(connector.getAPIKey());

		String input_url = (String) request.getParameter("gurl");
		StringBuilder sb = new StringBuilder();
		String line;
		
		URL face_url = new URL(FACE_ENDPOINT_URL+"?url="+input_url+"&apikey="+connector.getAPIKey()+"&outputMode=json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(face_url.openStream()));
		while ((line = reader.readLine()) != null){
			sb.append(line);
		}
		try{
	 getInfo(sb,request,response);
		}catch(ParseException e){
			throw new ParseException("json" + e.getMessage(),1);
		
		}
			
			 //storeToMongo(age,gender,request,response);
		
	
	
		response.setContentType("text/html");
        response.setStatus(200);
        request.getRequestDispatcher("/viewinfo.jsp").forward(request, response);

	}
	
	
	public void getInfo(StringBuilder sb,HttpServletRequest request, HttpServletResponse response) throws ParseException{
				
			 JSONParser parser = new JSONParser();
			 try{
			 Object obj = parser.parse(sb.toString());
			  JSONObject object= (JSONObject) obj;
			 JSONArray face = (JSONArray) object.get("imageFaces");
			String age = null;
			String gender = null;
			   for(int i=0; i<face.size(); i++){
                JSONObject innerObj = (JSONObject) face.get((i));
			   innerObj = (JSONObject) innerObj.get("age");
			   age = innerObj.get("ageRange").toString();
			   gender = innerObj.get("gender").toString();				
			   }
			   	request.setAttribute("age",age);
		request.setAttribute("gender",gender);
			 }catch(ParseException pe){
				  pe.printStackTrace();
			 }
	
	}
	
	public void storeToMongo(String age,String gender,HttpServletRequest request, HttpServletResponse response){
		
	//MongoDBClient db = new MongoDBClient();
		
		String jsonString = "{\"age\":"+age+"\",\"gender\":"+gender+"\"}";
		//db.addEntry(jsonString);
		
		//request.setAttribute("result", db.getAll());
	}

}

