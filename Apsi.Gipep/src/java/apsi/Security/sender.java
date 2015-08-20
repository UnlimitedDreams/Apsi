/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apsi.Security;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Miguel Angel Lemos
 */
public class sender {

    /**
     * Ajuste de parametros de configuración de envío de correos
     *
     */
    public static class EnviadorMail {

        final String miCorreo = "info.apsi.noreply@gmail.com";
        final String miContraseña = "UnlimitedDreams";
        final String servidorSMTP = "smtp.gmail.com";
        final String puertoEnvio = "465";
        String mailReceptor = null;
        String asunto = null;
        String cuerpo = null;

        /**
         * Enviador de correos.
         *
         * @param mailReceptor Correo de quien se quiere enviar el correo
         * @param asunto Asunto del correo
         * @param cuerpo Mensaje del correo
         */
        public EnviadorMail(String mailReceptor, String asunto,
                String cuerpo) {
            this.mailReceptor = mailReceptor;
            this.asunto = asunto;
            this.cuerpo = cuerpo;

            Properties props = new Properties();
            props.put("mail.smtp.user", miCorreo);
            props.put("mail.smtp.host", servidorSMTP);
            props.put("mail.smtp.port", puertoEnvio);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.socketFactory.port", puertoEnvio);
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");

            SecurityManager security = System.getSecurityManager();

            try {
                Authenticator auth = new autentificadorSMTP();
                Session session = Session.getInstance(props, auth);
                // session.setDebug(true);

                MimeMessage msg = new MimeMessage(session);
                msg.setText(cuerpo);
                msg.setSubject(asunto);
                msg.setFrom(new InternetAddress(miCorreo));
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
                        mailReceptor));
                Transport.send(msg);
            } catch (Exception mex) {
                mex.printStackTrace();
            }
        }

        private class autentificadorSMTP extends javax.mail.Authenticator {

            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(miCorreo, miContraseña);
            }
        }

    }
}
