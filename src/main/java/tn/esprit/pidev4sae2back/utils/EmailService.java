package tn.esprit.pidev4sae2back.utils;

import java.util.List;

public interface EmailService {
    void sendEmail(List<String> recipients, String subject, String body);
}
