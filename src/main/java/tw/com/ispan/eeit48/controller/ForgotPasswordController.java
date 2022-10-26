package tw.com.ispan.eeit48.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.utility.RandomString;
import tw.com.ispan.eeit48.domain.EmpBean;
import tw.com.ispan.eeit48.service.EmpBeanNotFoundException;
import tw.com.ispan.eeit48.service.EmpService;

@Controller
public class ForgotPasswordController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmpService empService;
    

    @PostMapping("/forgotPassword")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);
        System.out.println("Email: " + email);
        System.out.println("Token: " + token);

        try {
            empService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/resetPassword.html?token=" + token;
            System.out.println("Link: " + resetPasswordLink);
            sendEmail(email, resetPasswordLink);
        } catch (EmpBeanNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (UnsupportedEncodingException | MessagingException e) {
            System.out.println(e.toString());
        }
        return "redirect:/resetPage.html";
    }

    private void sendEmail(String email, String resetPasswordLink)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom("howardwang904@gmail.com", "IBoss");
        helper.setTo(email);

        String subject = "IBoss 已為您重設密碼";

        String content = "<p>您好,</p>"
                + "<p>我們已收到您重設密碼的要求。</p>"
                + "<p>請點擊以下連結來重設您的密碼:</p>"
                + "<p><a href=\"" + resetPasswordLink + "\">更改您的密碼</a></p>"
                + "<br>"
                + "<p>假如您未發送重設您的密碼,表示可能有人盜用您的帳號。"
                + "請立即檢查帳號並盡速與我們聯絡。</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        javaMailSender.send(mimeMessage);
    }


     
    @PostMapping("/resetPassword")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
         
        EmpBean empBean = empService.get(token);
         
        if (empBean == null) {
            System.out.println("token"+token);
            return "/";
        } else {           
            empService.updatePassword(empBean, password);             
        }         
        return "redirect:/index.html";
    }
}
