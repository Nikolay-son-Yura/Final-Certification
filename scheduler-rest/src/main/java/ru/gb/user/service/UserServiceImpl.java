package ru.gb.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ru.gb.user.model.ConfirmationToken;
import ru.gb.user.model.User;
import ru.gb.user.repository.ConfirmationTokenRepository;
import ru.gb.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailService emailService;


    @Override
    public ResponseEntity<?> saveUser(User user) {
        if (userRepository.existsByUserEmail(user.getUserEmail())) {
            return ResponseEntity.badRequest().body("Ошибка: адрес электронной почты уже используется!");
        }

        userRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);//user

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUserEmail());
        mailMessage.setSubject("Завершить регистрацию!");
        mailMessage.setText("Чтобы подтвердить свою учетную запись, нажмите здесь : "
                + "http://localhost:8085/confirm-account?token=" + confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Подтвердите электронную почту по ссылке, отправленной на ваш адрес электронной почты");
    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByUserEmailIgnoreCase(token.getUser().getUserEmail());
//            user.setEnabled(true);
            userRepository.save(user);
            return ResponseEntity.ok("Электронная почта успешно подтверждена!");
        }
        return ResponseEntity.badRequest().body("Ошибка: не удалось подтвердить адрес электронной почты.");
    }
}
