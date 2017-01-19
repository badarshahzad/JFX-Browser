package segp3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

public class SampleTest {
  public static void main(String ... args ) throws DocumentException, IOException {    

      Document document = new Document();//make object of document
      PdfWriter writer = PdfWriter.getInstance(document,
          new FileOutputStream("pdf1.pdf"));//create object of PDFWriter which helps to create a new PDF file and write data of html into it.
      document.open(); //open document to write html data on it
      HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);//object of htmlpipelinecontext which help to handle images and the tags
      htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());//handling tags of html page
      htmlContext.setImageProvider(new AbstractImageProvider() {
          public String getImageRootPath() {
              return "C:/Users/student/Desktop/bootstrapiufuf/";//handling images and getting the root path where images are pres
          }
      }); 
      CSSResolver cssResolver =
            XMLWorkerHelper.getInstance().getDefaultCssResolver(true);//CSS resolver which helps to resolve CSS and create PDF
        Pipeline<?> pipeline =                                    //Pipeline used to join everything together in one document e.g
            new CssResolverPipeline(cssResolver,                  //resolving CSS of html page
                    new HtmlPipeline(htmlContext,                 //writing content of html page
                        new PdfWriterPipeline(document, writer)));//creating document as PDF
        XMLWorker worker = new XMLWorker(pipeline, true);//adding resolved html page to XMLWorker which parses the page and write it on document
        XMLParser p = new XMLParser(worker);
        p.parse(new FileInputStream("C:/Users/student/Desktop/bootstrap/index1.html"));
        document.close();//closing the document and showing the page converted into PDF
          System.out.println("Done.");        
    }}