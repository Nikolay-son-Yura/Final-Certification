package ru.gb.mail.confirmation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ru.gb.mail.confirmation.model.ConfirmationToken;
import ru.gb.mail.confirmation.repository.UserEmail;
import ru.gb.user.model.User;
import ru.gb.mail.confirmation.repository.ConfirmationTokenRepository;

@Service
public class UserServiceImpl {

    @Autowired
    private UserEmail userEmail;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailService emailService;


    public ResponseEntity<?> saveUser(User user) {
        if (userEmail.existsByUserEmail(user.getUserEmail())) {
            return ResponseEntity.badRequest().body("Ошибка: адрес электронной почты уже используется!");
        }

        userEmail.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);//user

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("nikola_doma@mail.ru");
        mailMessage.setTo(user.getUserEmail());
        mailMessage.setSubject("Завершить регистрацию!");
        mailMessage.setText("Чтобы подтвердить свою учетную запись, скопируйте ссылку : "
                + "http://localhost:8085/confirm-account?token=" + confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Подтвердите электронную почту по ссылке, отправленной на ваш адрес электронной почты");
    }

    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userEmail.findByUserEmailIgnoreCase(token.getUserEntity().getUserEmail());
//            user.setEnabled(true);
            userEmail.save(user);
            return ResponseEntity.ok("Электронная почта успешно подтверждена!");
        }
        return ResponseEntity.badRequest().body("Ошибка: не удалось подтвердить адрес электронной почты.");
    }
}
