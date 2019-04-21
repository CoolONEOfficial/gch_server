package ru.ruykarpuni.testservlet;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.json.JSONObject;
import ru.ruykarpuni.testservlet.error.FirebaseException;
import ru.ruykarpuni.testservlet.service.Firebase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PdfGeneration {
    static int FONT_SIZE_SMALL = 14;
    static int FONT_SIZE_BIG = 32;
    static int OFFSET = 40;

    public static void main(String[] args) throws Exception, FirebaseException {
       // createTemplate();
        Firebase firebase = new Firebase( "https://gch-dosday-ru.firebaseio.com/" );
        createFromJson(new JSONObject(firebase.get()));
//create("DWA","DWA","DWA","DWA","DWA");
    }

    public static void create(String to, String toName, String from, String full, String apeal) throws Exception {
        Document document = new Document();


        PdfWriter.getInstance(document,
                new FileOutputStream("template.pdf"));

        document.open();

        BaseFont russianBaseTo = BaseFont.createFont("/Users/ruykarpuni/Downloads/TimesNewRoman.ttf","cp1251",BaseFont.EMBEDDED);
        Paragraph p1=new Paragraph(to,new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_RIGHT);
        document.add(p1);
        p1=new Paragraph(toName,new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_RIGHT);
        document.add(p1);
        p1=new Paragraph(from,new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_RIGHT);
        document.add(p1);
        p1=new Paragraph("\nУважаемый "+ full+",\n\n",new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_LEFT);
        document.add(p1);
        p1=new Paragraph(apeal+"\n\n",new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_LEFT);
        document.add(p1);
        p1=new Paragraph("Приложение: Копии подписей жителей",new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_LEFT);
        document.add(p1);
        p1=new Paragraph("Приложение: Фотографии от граждан с места происшествия"+"\n\n",new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_LEFT);
        document.add(p1);
        p1=new Paragraph("Фамилия Имя Отчество"+"\n",new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_LEFT);
        document.add(p1);
        p1=new Paragraph("Подпись"+"\n",new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_LEFT);
        document.add(p1);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        //System.out.println(dateFormat.format(cal));
       // p1=new Paragraph(dateFormat.format(cal)+"\n\n",new Font(russianBaseTo,14));
        p1=new Paragraph("DAte\n\n",new Font(russianBaseTo,14));

        p1.setAlignment(Element.ALIGN_LEFT);
        document.add(p1);
        createDataTable(document);
        createPhotoDataTable(document);

        //Paragraph to = new Paragraph("To: somebody", fontTo);
       // to.setAlignment(Element.ALIGN_RIGHT);
//        document.add(to);
//
//        // отцентрированный параграф
//        Paragraph title = new Paragraph("Title", font1);
//        title.setAlignment(Element.ALIGN_CENTER);
//        title.setSpacingAfter(FONT_SIZE_BIG);
//        document.add(title);
//
//        // параграф с текстом
//        Paragraph purpose = new Paragraph("Text", font2);
//        purpose.setSpacingAfter(FONT_SIZE_BIG);
//        document.add(purpose);
//
//        // параграф с добавленным чанком текста
//        Paragraph amount = new Paragraph();
//        amount.setFont(font2);
//        amount.setSpacingAfter(8);
//        amount.add(new Chunk("Amount"));
//        document.add(amount);
//
//        // параграф с фразой, в которую добавлен чанк
//        Paragraph date = new Paragraph();
//        date.setFont(font2);
//        Phrase phrase = new Phrase();
//        phrase.add(new Chunk("Date"));
//        date.add(phrase);
//        document.add(date);
//
//        document.add(new Paragraph("Name", font2));
//
//        Paragraph footer = new Paragraph(
//                "Important - please retain for your records - ");
//
//        // ссылка
//        Anchor anchor = new Anchor("Javenue");
//        anchor.setReference("http://www.javenue.info");
//        footer.add(anchor);
//
//        footer.setAlignment(Element.ALIGN_CENTER);
//        footer.setSpacingBefore(FONT_SIZE_BIG);
//        document.add(footer);
//
//        // картинка, загруженная по URL
//        String imageUrl = "http://www.javenue.info/files/sample.png";
//        // Image.getInstance("sample.png")
//        Image stamp = Image.getInstance(new URL(imageUrl));
//        stamp.setAlignment(Element.ALIGN_RIGHT);
//        document.add(stamp);




        document.close();
    }


    private static void createDataTable(Document document) throws DocumentException, IOException {
        BaseFont russianBaseTo = BaseFont.createFont("/Users/ruykarpuni/Downloads/TimesNewRoman.ttf","cp1251",BaseFont.EMBEDDED);
        PdfPTable table = new PdfPTable(4);
        table.setWidths(new float[] { 0.5f, 3,2,1.5f });
        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("#", new Font(russianBaseTo,14)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Фамилия имя отчество", new Font(russianBaseTo,14)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Адрес Телефон", new Font(russianBaseTo,14)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Подпись",new Font(russianBaseTo,14)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("2.1");
        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("2.1");


        document.add(table);

    }

    private static void createPhotoDataTable(Document document) throws DocumentException, IOException {
        String imageUrl = "http://www.javenue.info/files/sample.png";
        Image stamp = Image.getInstance(new URL(imageUrl));
document.add(stamp);
        stamp = Image.getInstance(new URL("https://as2.ftcdn.net/jpg/00/33/51/03/500_F_33510336_y8iRqPVkUOqaA37fnNop2exwBQT4GzK4.jpg"));
        document.add(stamp);
    }

    public static void createFromJson(JSONObject jsonObject) throws IOException, DocumentException, FirebaseException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("template2.pdf"));
        document.open();
        BaseFont russianBaseTo = BaseFont.createFont("/Users/ruykarpuni/Downloads/TimesNewRoman.ttf","cp1251",BaseFont.EMBEDDED);
        Paragraph p1=new Paragraph("Губернатору нижегородской области",new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_RIGHT);
        document.add(p1);
        p1=new Paragraph("Никитину Г.С.",new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_RIGHT);
        document.add(p1);
        p1=new Paragraph("от жителей города",new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_RIGHT);
        document.add(p1);
        p1=new Paragraph("\nУважаемый "+ "Глеб Никитич"+",\n\n",new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_LEFT);
        document.add(p1);
        p1=new Paragraph("Мы, жители Нижнего Новгорода, просим решить следующий вопрос:"+"\n\n",new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_LEFT);
        document.add(p1);
        p1=new Paragraph("Приложение: Подписи жителей",new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_LEFT);
        document.add(p1);
        p1=new Paragraph("Приложение: Фотографии от граждан с места происшествия"+"\n\n",new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_LEFT);
        document.add(p1);
        p1=new Paragraph("ФИО"+"\n",new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_LEFT);
        document.add(p1);
        p1=new Paragraph("Адрес, телефон"+"\n",new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_LEFT);
        document.add(p1);
        p1=new Paragraph("Подпись"+"\n",new Font(russianBaseTo,14));
        p1.setAlignment(Element.ALIGN_LEFT);
        document.add(p1);
        createDataTable(jsonObject,document,0);
        //createPhotoDataTable2(document,0);
       // createPhotoDataTable(document);
        document.close();

    }

    private static void createDataTable(JSONObject json, Document document,int tid) throws DocumentException, IOException, FirebaseException {
        BaseFont russianBaseTo = BaseFont.createFont("/Users/ruykarpuni/Downloads/TimesNewRoman.ttf","cp1251",BaseFont.EMBEDDED);
        PdfPTable table = new PdfPTable(4);
        table.setWidths(new float[] { 0.5f, 3,2,1.5f });

        PdfPCell c1 = new PdfPCell(new Phrase("#", new Font(russianBaseTo,14)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Фамилия имя отчество", new Font(russianBaseTo,14)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Адрес Телефон", new Font(russianBaseTo,14)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Подпись",new Font(russianBaseTo,14)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

       table.setHeaderRows(1);


        FirebaseConnection firebaseConnection = new FirebaseConnection("https://gch-dosday-ru.firebaseio.com/","AIzaSyBHuqE27uLKjGCoCkBPkbcaQ-uuLX0zJDM");
JSONObject jsonObject = new JSONObject(firebaseConnection.get());

        for (int i = 1; i<5; i++) {
            table.addCell(i+"");
//            System.out.println(json);
            String uuid = jsonObject.getJSONArray("tasks").getJSONObject(tid).getJSONArray("votes").get(i).toString();
            System.out.println(uuid);
            System.out.println(jsonObject.getJSONObject("users").has(uuid));
            if(!jsonObject.getJSONObject("users").has(uuid))
            {
                c1 = new PdfPCell(new Phrase("Имя, Фамилия, Отчество",new Font(russianBaseTo,14)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase("Адрес 1234567890",new Font(russianBaseTo,14)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase("подпись",new Font(russianBaseTo,14)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
               table.addCell(c1);
            } else {
                c1 = new PdfPCell(new Phrase(jsonObject.getJSONObject("users").getJSONObject(uuid).getString("fullName"),new Font(russianBaseTo,14)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(jsonObject.getJSONObject("users").getJSONObject(uuid).getString("address") + " " + jsonObject.getJSONObject("users").getJSONObject(uuid).getString("phoneNumber"),new Font(russianBaseTo,14)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase("подпись",new Font(russianBaseTo,14)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
            }


        }





        document.add(table);
        createPhotoDataTable2(document,0,jsonObject);

    }

    private static void createPhotoDataTable2(Document document,int taskId,JSONObject jsonObject) throws DocumentException, IOException, FirebaseException {
      //  FirebaseConnection firebaseConnection = new FirebaseConnection("https://gch-dosday-ru.firebaseio.com/","AIzaSyBHuqE27uLKjGCoCkBPkbcaQ-uuLX0zJDM");
       // JSONObject jsonObject = new JSONObject(firebaseConnection.get());

        if (jsonObject.getJSONArray("tasks").getJSONObject(taskId).has("photoUrls"))
        {
            System.out.println("true");
            for (Object object : jsonObject.getJSONArray("tasks").getJSONObject(taskId).getJSONArray("photoUrls").toList())
            {
                String imageUrl = ((String)object);
                Image stamp = Image.getInstance(new URL(imageUrl));
                document.add(stamp);

            }




        }

//
    }

}