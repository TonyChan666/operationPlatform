package cn.com.bmsoft.pdf;



/**
 * Created by wgl on 2019/9/19.
 */

        import com.lowagie.text.Document;
        import com.lowagie.text.pdf.PdfWriter;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.util.Map;

public interface PdfExportService {

        public void make(Map<String,Object> model, Document document,
                         PdfWriter writer, HttpServletRequest request,
                         HttpServletResponse response);

}