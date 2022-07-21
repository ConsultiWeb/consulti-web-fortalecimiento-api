package com.consulti.apiconsulti;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Properties;

import javax.imageio.ImageIO;

import com.pspdfkit.api.PSPDFKit;
import com.pspdfkit.api.PSPDFKitInitializeException;
import com.pspdfkit.api.PdfDocument;
import com.pspdfkit.api.providers.FileDataProvider;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@ComponentScan({ "com.consulti" })
@EnableAsync
public class ApiConsultiApplication {
  public static void main(String[] args) {
    SpringApplication.run(ApiConsultiApplication.class, args);
  }

  @Value("${mail.host}")
  String host;

  @Value("${mail.username}")
  String username;

  @Value("${mail.password}")
  String password;

  @Value("${mail.port}")
  Integer port;

  @Value("${mail.auth}")
  String auth;

  @Value("${pdfKitKey}")
  String pdfKitKey;

  @Bean
  public ServletRegistrationBean<DispatcherServlet> dispatcherServletServletRegistrationBean(
      DispatcherServlet dispatcherServlet) {
    ServletRegistrationBean<DispatcherServlet> bean = new ServletRegistrationBean<DispatcherServlet>(dispatcherServlet,
        "/api-consulti/*");
    bean.setAsyncSupported(true);
    bean.setName("api-consulti");
    bean.setLoadOnStartup(1);
    return bean;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean(name = "multipartResolver")
  public CommonsMultipartResolver multipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(1000000);
    return multipartResolver;
  }

  @Bean
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(host);
    mailSender.setPort(port);

    mailSender.setUsername(username);
    mailSender.setPassword(password);

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", auth);
    props.put("mail.smtp.starttls.enable", "false");
    props.put("mail.debug", "true");

    return mailSender;
  }

  @Bean
  public void initializePSPDFKit() throws PSPDFKitInitializeException {
    // PSPDFKit.initialize(pdfKitKey);
    // // Open a document to work on.
    // File file = new File("assets/default.pdf");
    // PdfDocument document = new PdfDocument(new FileDataProvider(file));

    // // Add a new stamp annotation.
    // JSONObject jsonObject = new JSONObject();
    // jsonObject.put("bbox", new float[] { 0, 0, 100, 50 });
    // jsonObject.put("pageIndex", 0);
    // jsonObject.put("type", "pspdfkit/stamp");
    // jsonObject.put("stampType", "Approved");
    // jsonObject.put("opacity", 1);
    // jsonObject.put("v", 1);
    // document.getAnnotationProvider().addAnnotationJson(jsonObject);

    // // Export the changes to Instant Document JSON.
    // File jsonFile = new File("out/instantOutput.json");
    // if (jsonFile.createNewFile()) {
    // document.exportDocumentJson(new FileDataProvider(jsonFile));
    // }

    // // Render the first page and save to a PNG.
    // BufferedImage image = document.getPage(0).renderPage();
    // File pngfile = new File("out/test.png");
    // boolean success = ImageIO.write(image, "png", pngfile);
  }
}
