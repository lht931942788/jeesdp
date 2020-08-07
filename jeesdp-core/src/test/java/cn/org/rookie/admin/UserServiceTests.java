package cn.org.rookie.admin;

import cn.org.rookie.admin.configuration.Demo;
import cn.org.rookie.admin.service.UserService;
import cn.org.rookie.common.base.entity.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    Demo demo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void contextLoads() {
        User admin = (User) userService.loadUserByUsername("admin");
        try {
            User user = admin.clone();
            System.out.println(user.getUserType());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(admin.getUserType());
    }

    @Test
    public void testMail() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("931942788@qq.com");
        helper.setTo("931942788@qq.com");
        helper.setSubject("11111");
        helper.setText("<html><body>带静态资源的邮件内容，这个一张IDEA配置的照片</body></html>", true);
        javaMailSender.send(message);
    }

    @Test
    public void testPattern() throws Exception {
        String context = "demo#{name}demo";
        Pattern pattern = Pattern.compile("#\\{(.*)}");
        Matcher matcher = pattern.matcher(context);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void testPassword() throws Exception {
        String admin = passwordEncoder.encode("admin");
        System.out.println(admin);
    }

/*    @Test
    public void testHtmlToPdf() throws Exception {
        ConverterProperties properties = new ConverterProperties();
        FontProvider provider = new FontProvider();
        provider.addFont("");
        properties.setFontProvider(provider);
        HtmlConverter.convertToPdf(new File("C:\\Users\\lihaitao\\Desktop\\ddd.html")
                , new File("C:\\Users\\lihaitao\\Desktop\\ddd.pdf"), properties);
    }

    @Test
    public void testPdf() throws Exception {
        PdfReader reader = new PdfReader("C:\\Users\\lihaitao\\Desktop\\3.pdf");
        PdfDocument document = new PdfDocument(reader);
        int pageSize = document.getNumberOfPages();
        PdfPage page = document.getPage(20);
        PdfResources resources = page.getResources();
        Set<PdfName> resourceNames = resources.getResourceNames();
        for (PdfName pdfName : resourceNames) {
            System.out.println(pdfName);
            PdfDictionary resource = resources.getResource(pdfName);
            PdfImageXObject image = resources.getImage(pdfName);
            System.out.println(image);
        }
    }*/

    @Test
    public void testJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree("");
    }

    @Test
    public void testDemo() throws Exception {
        System.out.println(demo.getDemo());
    }

    @Test
    public void testTime() throws Exception {
        Calendar calendar = Calendar.getInstance();

        System.out.println(calendar.get(Calendar.YEAR));

    }

}
