package ru.ruykarpuni.testservlet.demo;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONObject;
import ru.ruykarpuni.testservlet.FirebaseConnection;
import ru.ruykarpuni.testservlet.PdfGeneration;
import ru.ruykarpuni.testservlet.error.FirebaseException;
import ru.ruykarpuni.testservlet.error.JacksonUtilityException;
import ru.ruykarpuni.testservlet.service.Firebase;

import javax.mail.MessagingException;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Demo {

public static String testData;

	public static void main(String[] args) throws FirebaseException, JsonParseException, JsonMappingException, IOException, JacksonUtilityException, MessagingException, DocumentException {

		
//		// get the base-url (ie: 'http://gamma.firebase.com/username')
//		String firebase_baseUrl = "https://gch-dosday-ru.firebaseio.com/";
//
//		// get the api-key (ie: 'tR7u9Sqt39qQauLzXmRycXag18Z2')
//		String firebase_apiKey = "AIzaSyBHuqE27uLKjGCoCkBPkbcaQ-uuLX0zJDM";
//
//		for( String s : args ) {
//
//			if( s == null || s.trim().isEmpty() ) continue;
//			String[] split = s.trim().split( "=" );
//
//			if( split[0].equals("baseUrl") ) {
//				firebase_baseUrl = split[1];
//			}
//			else if( split[0].equals("apiKey") ) {
//				firebase_apiKey = split[1];
//			}
//
//
//		}
//		if( firebase_baseUrl == null || firebase_baseUrl.trim().isEmpty() ) {
//			throw new IllegalArgumentException( "Program-argument baseUrl not found but required" );
//		}
//
//
//		// create the firebase
//		Firebase firebase = new Firebase( firebase_baseUrl );
//
//
//		// "DELETE" (the fb4jDemo-root)
//		FirebaseResponse response ;


		File file = new File("/Users/ruykarpuni/Desktop/gch_server/src/main/java/ru/ruykarpuni/testservlet/demo/handle.txt");
		do {
		while (!file.exists())
		{}
			try{
				FileInputStream fstream = new FileInputStream("/Users/ruykarpuni/Desktop/gch_server/src/main/java/ru/ruykarpuni/testservlet/demo/mainFile.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String uuid = br.readLine();
				String taskId = br.readLine();
				FirebaseConnection firebaseConnection = new FirebaseConnection("https://gch-dosday-ru.firebaseio.com/","AIzaSyBHuqE27uLKjGCoCkBPkbcaQ-uuLX0zJDM");
				System.out.println( "\n\nResult of GET:\n" +firebaseConnection.get());




				String resJS=firebaseConnection.get();
				JSONObject jsonObject= new JSONObject(resJS);
				jsonObject.getJSONArray("tasks").getJSONObject(Integer.valueOf(taskId)).getJSONArray("votes").put(uuid);

				Map<String, Object> retMap = new Gson().fromJson(
						jsonObject.toString(), new TypeToken<LinkedHashMap<String, Object>>() {}.getType()
				);

				Firebase firebase = new Firebase( "https://gch-dosday-ru.firebaseio.com/" );
				firebase.put( retMap );

				PdfGeneration.createFromJson(jsonObject);


				//EmailAttachmentSender.sendEmailWithAttachments();

				deleteFile("/Users/ruykarpuni/Desktop/gch_server/src/main/java/ru/ruykarpuni/testservlet/demo/mainFile.txt");
				deleteFile("/Users/ruykarpuni/Desktop/gch_server/src/main/java/ru/ruykarpuni/testservlet/demo/handle.txt");

			}catch (IOException e){
				System.out.println("Ошибка");
			}



		} while (!file.exists());

//		GoogleMail.Send();
//		System.out.println( "\n\nResult of GET:\n" +firebaseConnection.get());

		//	response	= firebase.delete();


		// "PUT" (test-map into the fb4jDemo-root)
//		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
//		dataMap.put( "PUT-root", "Try" );
//		response = firebase.put( dataMap );
//		System.out.println( "\n\nResult of PUT (for the test-PUT to fb4jDemo-root):\n" + response );
//		System.out.println("\n");


		// "GET" (the fb4jDemo-root)
//		response = firebase.get();
//		System.out.println( "\n\nResult of GET:\n" + response.getRawBody() );
//		System.out.println("\n");
//
//
//
//
//
//		// "POST" (test-map into a sub-node off of the fb4jDemo-root)
//		response = firebase.post( "test-POST", dataMap );
//		System.out.println( "\n\nResult of POST (for the test-POST):\n" + response );
//		System.out.println("\n");
//
//
//		// "DELETE" (it's own test-node)
//		dataMap = new LinkedHashMap<String, Object>();
//		dataMap.put( "DELETE", "This should not appear; should have been DELETED" );
//		response = firebase.put( "test-DELETE", dataMap );
//		System.out.println( "\n\nResult of PUT (for the test-DELETE):\n" + response );
//		response = firebase.delete( "test-DELETE");
//		System.out.println( "\n\nResult of DELETE (for the test-DELETE):\n" + response );
//		response = firebase.get( "test-DELETE" );
//		System.out.println( "\n\nResult of GET (for the test-DELETE):\n" + response );

		// Sign Up user for Firebase's Auth Service demo (https://firebase.google.com/docs/reference/rest/auth/)
//		if(firebase_apiKey != null) {
//
//			firebase = new Firebase("https://www.googleapis.com/identitytoolkit/v3/relyingparty", false);
//			firebase.addQuery("key", firebase_apiKey);
//
//			dataMap.clear();
//			dataMap.put("email", "elonmusk@tesla.com");
//			dataMap.put("password", "TeslaRocks");
//			dataMap.put("returnSecureToken", true);
//
//			response = firebase.post("signupNewUser", dataMap);
//			System.out.println("\n\nResult of Signing Up:\n" + response);
//			System.out.println("\n");
//
//		} else {
//			System.out.println("\n\nResult of Signing Up:\n failed, because no API Key was provided.");
//			System.out.println("\n");
//		}

	}
//иначе не получается подключиться к Firebase DB из сервлета
	public static void generateTrigerFiles(String name1,String name2,String text) throws FirebaseException, UnsupportedEncodingException {
		if (text.equals("")){
			deleteFile(name2);
			deleteFile(name1);
		} else {
			createFile(name1,text);
			createFile(name2,"");

		}

	}

	public static void deleteFile(String name){
		File file = new File(name);
		if(file.delete()){
		}else System.out.println("File doesn't exist ");
	}

	public static void createFile(String name,String text){
		File file = new File(name);

		try {
			//проверяем, что если файл не существует то создаем его
			if(!file.exists()){
				file.createNewFile();
			}

			PrintWriter out = new PrintWriter(file.getAbsoluteFile());

			try {
				//Записываем текст в файл
					out.print(text);

			} finally {
				//После чего мы должны закрыть файл
				//Иначе файл не запишется
				out.close();
			}
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}




