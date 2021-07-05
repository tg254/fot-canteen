package com.hello.FirstApp.functionalities;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDFGen {
    public void createPDF(int code, String text){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(Integer.toString(code) + "Payment.pdf"));

            document.open();
            Font fontSmall = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Font fontLarge = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.GREEN);
            Chunk chunk1 = new Chunk("Payment Receipt\n", fontSmall);
            Chunk chunk2 = new Chunk(text, fontSmall);

            document.add(chunk1);
            document.close();
        } catch (Exception ex){

        }

    }
}
