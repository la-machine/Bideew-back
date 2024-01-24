package com.example.bideew.service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bideew.Dto.ContactMessage;
import com.example.bideew.Dto.LoginRequest;
import com.example.bideew.Dto.LoginResponse;
import com.example.bideew.Dto.RegistrationRequest;
import com.example.bideew.Dto.SubscribeRequest;
import com.example.bideew.model.Role;
import com.example.bideew.model.User;
import com.example.bideew.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private EmailSender emailSender;
        public ResponseEntity<?> userRegistration(RegistrationRequest reqUser){
                User tempUser = new User();
                Optional<User> userExist = userRepository.findByEmail(reqUser.getEmail());
                if(userExist.isPresent()){
                        return ResponseEntity.badRequest().body("User already exist");
                }
                String token = UUID.randomUUID().toString();
                tempUser.setEmail(reqUser.getEmail());
                tempUser.setName(reqUser.getName());
                tempUser.setPassword(passwordEncoder.encode(reqUser.getPassword()));
                tempUser.setRole(Role.Admin);
                tempUser.setVerificationToken(token);
                tempUser.setEnable(true);
                userRepository.save(tempUser);              
                String jwtToken = jwtService.generateToken(tempUser);
                String link = "http://localhost:8080/api/verify?email=" + tempUser.getEmail() + "&code=" + tempUser.getVerificationToken();;
                emailSender.send(reqUser.getEmail(),
                        buildEmail(reqUser.getName(), link));
                
                return ResponseEntity.status(201).body(LoginResponse.builder().token(jwtToken).name(tempUser.getName())
                .email(tempUser.getEmail()).build());
        }

        public void sendMessage(ContactMessage message){
                emailSender.sendEmail(message);
                
        }

        public ResponseEntity<?> subscribe(SubscribeRequest subcribe){
                Optional<User> userExist = userRepository.findByEmail(subcribe.getEmail());               
                if(userExist.isPresent()){
                        System.out.println("====== Doing the check========");
                    return ResponseEntity.badRequest().body(userExist.get().getEmail());
                }
                User tempUser = new User();
                tempUser.setEmail(subcribe.getEmail());
                // tempUser.setName(subcribe.getName());
                tempUser.setRole(Role.Subscriber);
                userRepository.save(tempUser);
                return ResponseEntity.ok().body(Collections.singletonMap("message", "User subscribe successfully"));
        }

        public boolean verifyUser(String email, String verificationCode) {
                Optional<User> user = userRepository.findByEmail(email);
                if (user.isPresent() && user.get().getVerificationToken().equals(verificationCode)) {
                    user.get().setEnable(true);
                    userRepository.save(user.get());
                    return true;
                } else {
                    return false;
                }
        }

        public ResponseEntity<?> authenticate(LoginRequest request){
                authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
                User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
                String jwtToken = jwtService.generateToken(user);
                LoginResponse response = LoginResponse.builder().email(user.getEmail()).name(user.getName())
                        .token(jwtToken).build();
                return ResponseEntity.status(200).body(response);
        }


        public String buildEmail(String name, String link) {
                return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                        "\n" +
                        "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                        "\n" +
                        "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                        "    <tbody><tr>\n" +
                        "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                        "        \n" +
                        "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                        "          <tbody><tr>\n" +
                        "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                        "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                        "                  <tbody><tr>\n" +
                        "                    <td style=\"padding-left:10px\">\n" +
                        "                  \n" +
                        "                    </td>\n" +
                        "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                        "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                        "                    </td>\n" +
                        "                  </tr>\n" +
                        "                </tbody></table>\n" +
                        "              </a>\n" +
                        "            </td>\n" +
                        "          </tr>\n" +
                        "        </tbody></table>\n" +
                        "        \n" +
                        "      </td>\n" +
                        "    </tr>\n" +
                        "  </tbody></table>\n" +
                        "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                        "    <tbody><tr>\n" +
                        "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                        "      <td>\n" +
                        "        \n" +
                        "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                        "                  <tbody><tr>\n" +
                        "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                        "                  </tr>\n" +
                        "                </tbody></table>\n" +
                        "        \n" +
                        "      </td>\n" +
                        "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                        "    </tr>\n" +
                        "  </tbody></table>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                        "    <tbody><tr>\n" +
                        "      <td height=\"30\"><br></td>\n" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                        "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                        "        \n" +
                        "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                        "        \n" +
                        "      </td>\n" +
                        "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "      <td height=\"30\"><br></td>\n" +
                        "    </tr>\n" +
                        "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                        "\n" +
                        "</div></div>";
        }

}
