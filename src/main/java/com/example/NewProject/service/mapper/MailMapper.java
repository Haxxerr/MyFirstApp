package com.example.NewProject.service.mapper;

import com.example.NewProject.domain.Mail;
import com.example.NewProject.service.dto.MailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface MailMapper extends EntityMapper<MailDTO, Mail> {

  @Mapping(source = "user.id",target = "userId")
  @Mapping(source = "user.email",target = "userEmail")
  MailDTO toDto(Mail mail);

  @Mapping(source = "userId", target = "user")
  Mail toEntity(MailDTO mailDTO);

  default Mail fromId(Long id) {
    if (id == null) {
      return null;
    }
    Mail mail = new Mail();
    mail.setId(id);
    return mail;
  }
}
