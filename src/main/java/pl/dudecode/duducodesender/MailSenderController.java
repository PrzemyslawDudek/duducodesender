package pl.dudecode.duducodesender;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;

@Controller
public class MailSenderController {

    private final MailSenderService mailSenderService;
    private String message = null;

    public MailSenderController(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @ModelAttribute("message")
    public String getMessage() {
        String info = message;
        message = null;
        return info;
    }

    @GetMapping("/")
    public String createMail(Model model) {
        model.addAttribute("mailSenderForm", new MailSenderForm());
        return "create_mail";
    }

    @PostMapping("/send_mail")
    public String sendMail(MailSenderForm mailSenderForm) {
        //todo set sender maila and password
        try {
            mailSenderService.sendMail(
                    mailSenderForm.getMailTo(),
                    mailSenderForm.getSubject(),
                    mailSenderForm.getContent(),
                    false
            );
            message = "Success";
        } catch (MessagingException e) {
            e.printStackTrace();
            message = "Error";
        }
        return "redirect:/";
    }
}
