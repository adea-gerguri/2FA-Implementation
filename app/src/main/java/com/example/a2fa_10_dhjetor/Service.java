package com.example.a2fa_10_dhjetor;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Service {
    public static void sendEmail(String recipient, String subject, String body) {

        new Thread(() -> {
            try {
                System.out.println("Thread created for sending email...");

                    Properties props = new Properties();
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");

                    Session session = Session.getInstance(props, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("email", "apppassword");
                        }
                    });

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("email"));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
                    message.setSubject(subject);
                    message.setText(body);

                    System.out.println("Messsage is being sent here");
                    Transport.send(message);
                    System.out.println("Message sent successfully to " + recipient);

                } catch (MessagingException e) {
                    e.printStackTrace();
                    System.err.println("Error sending email: " + e.getMessage());
                }
            }).start();
        }
    }


