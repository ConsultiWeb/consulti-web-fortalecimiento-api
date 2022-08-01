package com.consulti.apiconsulti.service.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.ResetToken;
import com.consulti.apiconsulti.entity.User;
import com.consulti.apiconsulti.model.*;
import com.consulti.apiconsulti.repository.ResetTokenRespository;
import com.consulti.apiconsulti.repository.UserRepository;
import com.consulti.apiconsulti.security.JwtTokenUtil;
import com.consulti.apiconsulti.service.AuthService;
import com.consulti.apiconsulti.service.MailService;
import com.consulti.apiconsulti.service.UserService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.constants.SecurityConstants;
import com.consulti.apiconsulti.utils.validations.AuthValidations;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {
  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  UserRepository userRepository;

  @Autowired
  ResetTokenRespository resetTokenRespository;

  @Autowired
  private AuthValidations authValidations;

  @Autowired
  UserService userService;

  @Autowired
  MailService mailService;

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Value("${google.clientId}")
  String googleClientId;
  @Value("${linkedin.clientId}")
  String linkedinId;
  @Value("${linkedin.secret}")
  String linkedinSecret;
  @Value("${linkedin.url_access_token}")
  String urlAccessToken;
  @Value("${linkedin.url_get_basic_profile}")
  String urlBasicProfile;
  @Value("${linkedin.url_redirect_url}")
  String urlRedirect;

  public JwtResponse auth(JwtRequest request) throws GenericException {
    JwtResponse response;
    try {
      User user = userRepository.findByUsername(request.getUsername());
      authValidations.authenticate(request.getUsername(), request.getPassword());
      final UserDetails userDetails = loadUserByUsername(request.getUsername());
      final String token = jwtTokenUtil.generateToken(userDetails);
      response = new JwtResponse(token,userDetails.getUsername());
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
  public JwtResponse socialAuth(String email) throws GenericException {
    JwtResponse response;
    try {
      User user = userRepository.findByEmail(email);
      if (user == null) {
        throw new GenericException("Usuario no encontrado");
      }
      final UserDetails userDetails = loadUserByUsername(user.getUsername());
      final String token = jwtTokenUtil.generateToken(userDetails);
      response = new JwtResponse(token,userDetails.getUsername());
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
  @Override
  public String authFacebook(TokenDto tokenDto) throws GenericException {
    String response;
    final String [] fields = {"email"};
    try {
      Facebook facebook = new FacebookTemplate(tokenDto.getValue());
      org.springframework.social.facebook.api.User user = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class,fields);
      if(user!=null){
        response = user.getEmail();
      }else{
        throw new GenericException("Usuario no encontrado ");
      }
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
  @Override
  public String authGoogle(TokenDto tokenDto) throws IOException {
    String response;
    try {
      final NetHttpTransport transport = new NetHttpTransport();
      final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
      GoogleIdTokenVerifier.Builder verifier =
              new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                      .setAudience(Collections.singletonList(googleClientId));
      final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDto.getValue());
      final GoogleIdToken.Payload payload = googleIdToken.getPayload();
      if(payload!=null){
        response = payload.getEmail();
      }else{
        throw new GenericException("Usuario no encontrado ");
      }
    } catch (GenericException e) {
      throw new IOException(e.getMessageError());
    }
    return response;
  }
  @Override
  public String authLinkedin(TokenDto tokenDto) throws IOException  {
    String response;
    try {
      HttpPost post = new HttpPost(urlAccessToken);
      List<NameValuePair> urlParameters = new ArrayList<>();
      urlParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
      urlParameters.add(new BasicNameValuePair("code", tokenDto.getValue()));
      urlParameters.add(new BasicNameValuePair("redirect_uri", urlRedirect));
      urlParameters.add(new BasicNameValuePair("client_id", linkedinId));
      urlParameters.add(new BasicNameValuePair("client_secret", linkedinSecret));
      post.setEntity(new UrlEncodedFormEntity(urlParameters));
      try (CloseableHttpClient httpClient = HttpClients.createDefault();
           CloseableHttpResponse responseHttp = httpClient.execute(post)){
          Gson gson = new Gson();
          JsonObject jsonObj = gson.fromJson(EntityUtils.toString(responseHttp.getEntity()),JsonObject.class);
          if(!jsonObj.has("access_token")){
            throw new GenericException("Ha ocurrido un error");
          }
          response = getInfoLinkedin( jsonObj.get("access_token").getAsString());
      }
    } catch (GenericException e) {
      throw new IOException(e.getMessageError());
    }
    return response;
  }
  public String getInfoLinkedin(String access_token) throws IOException {
    HttpGet post = new HttpGet(urlBasicProfile);
    post.addHeader("content-type", "application/json");
    post.addHeader("Authorization", "Bearer "+access_token);
    List<NameValuePair> urlParameters = new ArrayList<>();
    String emailLinkedin="";
    try (CloseableHttpClient httpClient = HttpClients.createDefault();
         CloseableHttpResponse response = httpClient.execute(post)) {
        ObjectMapper mapper = new ObjectMapper();
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<JsonObject>>(){}.getType();
        JsonObject jsonObj = gson.fromJson(EntityUtils.toString(response.getEntity()),JsonObject.class);
        ArrayList<JsonObject> userArray =   gson.fromJson(jsonObj.get("elements").getAsJsonArray(),userListType);
        emailLinkedin =  userArray.get(0).get("handle~").getAsJsonObject().get("emailAddress").getAsString();
    }
    return emailLinkedin;
  }
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("Usuario no encontrado " + username);
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        new ArrayList<>());
  }
  public Boolean validateTokenPassword(String request) {
    return authValidations.validatePasswordResetToken(request);
  }
  public User confirmPassword(PasswordReqDTO request) throws GenericException {
    User response = new User();
    ResetToken token = resetTokenRespository.findByToken(request.getToken());
    Optional<User> user = userRepository.findById(token.getUser().getId());
    if (user.isPresent()) {
      User userUp = user.get();
      if(!bCryptPasswordEncoder.matches(request.getPassword(),userUp.getPassword())){
        userUp.setPassword(request.getPassword());
        userUp.setUsrModificacion(userUp.getUsername());
        response = userService.actualizar(userUp);
      }else{
        throw new GenericException("La contraseña debe ser distinta a la anterior");
      }
    }
    return response;
  }

  public Boolean resetearContrasena(HttpServletRequest request, String correo) throws UsernameNotFoundException {
    ResetToken myToken = new ResetToken();
    ResetTokenMailReqDTO requestResetPass = new ResetTokenMailReqDTO();
    User user = userRepository.findByEmail(correo);
    if (user == null) {
      throw new UsernameNotFoundException("Usuario no encontrado " + correo);
    }
    myToken.setToken(UUID.randomUUID().toString());
    myToken
        .setFechaExpiracion(GeneralUtils.agregarDiasAFecha(new Date(), SecurityConstants.EXPIRACION_DIAS_RESET_TOKEN));
    myToken.setUser(user);
    resetTokenRespository.save(myToken);
    requestResetPass.setContextPath(request.getContextPath());
    requestResetPass.setUser(user);
    requestResetPass.setToken(myToken.getToken());
    requestResetPass.setLocale(request.getLocale());
    return mailService.constructResetTokenEmail(requestResetPass);
  }
}
