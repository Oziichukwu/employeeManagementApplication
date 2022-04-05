package africa.semicolon.backendemployeemanagementsystem.email;

public interface EmailSender {

    void send(String toAddress, String subject, String body);
}
