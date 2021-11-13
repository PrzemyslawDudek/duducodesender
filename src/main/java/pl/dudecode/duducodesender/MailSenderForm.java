package pl.dudecode.duducodesender;

import lombok.Data;

@Data
public class MailSenderForm {

    private String mailTo;
    private String subject;
    private String content;

    private String mailFrom;
    private String password;

}
