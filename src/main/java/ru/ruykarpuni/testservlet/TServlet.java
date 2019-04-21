package ru.ruykarpuni.testservlet;

import ru.ruykarpuni.testservlet.demo.Demo;
import ru.ruykarpuni.testservlet.error.FirebaseException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class TServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/index.jsp").forward(request, response);

        FirebaseConnection firebaseConnection = null;
        try {
            String str = new FirebaseConnection("https://gch-dosday-ru.firebaseio.com/","AIzaSyBHuqE27uLKjGCoCkBPkbcaQ-uuLX0zJDM").get();
        } catch (FirebaseException e) {
            e.printStackTrace();
        }


        ServletContext context = getServletContext();
        try {
            context.log("test:"+ firebaseConnection.get());
        } catch (FirebaseException e) {
            e.printStackTrace();
        }


//        DB db = new DB();
//
//        FirebaseConnection firebaseConnection = null;
//        try {
//             firebaseConnection = new FirebaseConnection("https://gch-dosday-ru.firebaseio.com/","AIzaSyBHuqE27uLKjGCoCkBPkbcaQ-uuLX0zJDM");
//        } catch (FirebaseException e) {
//            e.printStackTrace();
//        }
//        try {
//            context.log(firebaseConnection.get());
//        } catch (FirebaseException e) {
//            e.printStackTrace();
//        }
        //      db.readdata(context);
       // request.getRequestDispatcher("/index.jsp").forward(request, response);
        //PrintWriter out = response.getWriter();

        //create Json Object
      //  JSONObject json = null;

        // put some value pairs into the JSON object .


//        try {
//            json = new JSONObject(firebaseConnection.get());
//        } catch (FirebaseException e) {
//            e.printStackTrace();
//        }
//        // finally output the json string
//        out.print(json.toString());






    }
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Demo.testData = request.getParameter("uuid")+"\n"+ request.getParameter("taskid");
        ServletContext context = getServletContext();


//        try {
//          //  context.log( "\n\nResult of GET:\n" +firebaseConnection.get());
//        } catch (FirebaseException e) {
//            e.printStackTrace();
//        }

        try {
            File file = new File("/Users/ruykarpuni/Desktop/gch_server/src/main/java/ru/ruykarpuni/testservlet/demo/handle.txt");
            while (file.exists())
            {}
            Demo.generateTrigerFiles("/Users/ruykarpuni/Desktop/gch_server/src/main/java/ru/ruykarpuni/testservlet/demo/mainFile.txt","/Users/ruykarpuni/Desktop/gch_server/src/main/java/ru/ruykarpuni/testservlet/demo/handle.txt", Demo.testData);
        } catch (FirebaseException e) {
            e.printStackTrace();
        }


        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Magic</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a POST. This is the magic.</p>");
        out.println("</body></html>");
        out.close();
    }

    public static void main(String[] args)
    {
        try {
        FirebaseConnection firebaseConnection = new FirebaseConnection("https://gch-dosday-ru.firebaseio.com/","AIzaSyBHuqE27uLKjGCoCkBPkbcaQ-uuLX0zJDM");
    } catch (FirebaseException e) {
        e.printStackTrace();
    }
    }


}
