package dev.ayush.authenticationservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ayush.authenticationservice.Dtos.SendEmailMessageDto;
import dev.ayush.authenticationservice.Dtos.UserDto;
import dev.ayush.authenticationservice.clients.KafkaProducerClient;
import dev.ayush.authenticationservice.models.Role;
import dev.ayush.authenticationservice.models.Session;
import dev.ayush.authenticationservice.models.SessionStatus;
import dev.ayush.authenticationservice.models.User;
import dev.ayush.authenticationservice.repositories.SessionRepository;
import dev.ayush.authenticationservice.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class AuthService {

    private SessionRepository sessionRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private KafkaProducerClient kafkaProducerClient;
    private ObjectMapper objectMapper;

    public AuthService(UserRepository userRepository,
                       SessionRepository sessionRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       KafkaProducerClient kafkaProducerClient,
                       ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.kafkaProducerClient = kafkaProducerClient;
        this.objectMapper = objectMapper;
    }


    public ResponseEntity<UserDto> login(String email, String password){
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("wrong username or password");

        }

      /* String token = RandomStringUtils.randomAlphanumeric(30);
        if we use random strings then we cant add any additional info in it and may end up making 2
        calls to the database of the auth server....so to reduce thius we will ise the JWTs.*/

        MacAlgorithm alg = Jwts.SIG.HS256; //or HS384 or HS256
        SecretKey key = alg.key().build();

        Map<String, Object> jsonForJwt = new HashMap<>();
        jsonForJwt.put("email", user.getEmail());
        jsonForJwt.put("roles", user.getRoles());
        jsonForJwt.put("createdAt: ", new Date());
        jsonForJwt.put("expiresAt", Date.from(LocalDate.now()
                .plusDays(3)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()));

        String token = Jwts.builder()
                .claims(jsonForJwt)
                .signWith(key, alg)
                .compact();


        Session session = new Session();

        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setUser(user);
        session.setToken(token);
        sessionRepository.save(session);

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token: " + token);

       ResponseEntity<UserDto> response = new ResponseEntity<>(UserDto.from(user), headers, HttpStatus.OK);
        return response;
    }

    public ResponseEntity<Void> logout(Long userId, String token){
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token,userId);

        if (sessionOptional.isEmpty()) return null;

        Session session = sessionOptional.get();

        session.setSessionStatus(SessionStatus.ENDED);

        sessionRepository.save(session);

        return ResponseEntity.ok().build();
    }

    public UserDto signup(String email, String password) throws JsonProcessingException {

        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        User savedUser = userRepository.save(user);
        UserDto userDto = UserDto.from(user);

        kafkaProducerClient.sendMessage("userSignUp", objectMapper.writeValueAsString(userDto));
        //we created this for the other consumers
        // for email -> each producer will tell what type of mail to send and the email service will just handle the sending part
        SendEmailMessageDto sendEmailMessageDto = new SendEmailMessageDto();
        sendEmailMessageDto.setTo(email);
        sendEmailMessageDto.setFrom("from admin@project.com");
        sendEmailMessageDto.setSubject("Welcome to the project");
        sendEmailMessageDto.setBody("thank you for signing up to the project application");

        kafkaProducerClient.sendMessage("sendEmail", objectMapper.writeValueAsString(sendEmailMessageDto));
        return userDto;
    }

    public SessionStatus validateToken(String token, Long userId) {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);

        if (sessionOptional.isEmpty()) {
            return SessionStatus.ENDED;
        }

        Session session = sessionOptional.get();

        if (!session.getSessionStatus().equals(SessionStatus.ACTIVE)) {
            return SessionStatus.ENDED;
        }

        Jws<Claims> jwsClaims = Jwts.parser()
                                        .build()
                                        .parseSignedClaims(token);


        String email = (String)jwsClaims.getPayload().get("email");
        List<Role> roles = (List<Role>)jwsClaims.getPayload().get("roles");
        Date createdAt = (Date) jwsClaims.getPayload().get("createdAt");
        Date expiresAt = (Date) jwsClaims.getPayload().get("expiresAt");

        long dateDiffInMillis = expiresAt.getTime() - createdAt.getTime();
        long diffInDays = (dateDiffInMillis/24 * 60 * 60 * 1000);
        if (diffInDays > 3) {
            return SessionStatus.ENDED;
        }

        return SessionStatus.ACTIVE;
    }
}
