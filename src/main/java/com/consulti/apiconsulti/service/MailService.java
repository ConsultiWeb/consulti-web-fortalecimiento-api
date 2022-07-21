package com.consulti.apiconsulti.service;

import com.consulti.apiconsulti.model.MailReqDTO;
import com.consulti.apiconsulti.model.ResetTokenMailReqDTO;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface MailService {
  @Async
  public Boolean sendSimpleMessage(MailReqDTO request);
  @Async
  public Boolean sendRawMessage(String request);

  public Boolean sendMessageWithAttachment(MailReqDTO request);

  public Boolean sendMessageWithMultipleAttachments(MailReqDTO request);

  public Boolean constructResetTokenEmail(ResetTokenMailReqDTO request);
  @Async
  public Boolean sendWithCCOnly(String de, String para, ArrayList<String> correos);
}
