package com.example.NewProject.service.mapper;

import com.example.NewProject.domain.Mail;
import com.example.NewProject.domain.User;
import com.example.NewProject.service.dto.MailDTO;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-06T18:31:01+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class SentEmailMapperImpl implements SentEmailMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Mail> toEntity(List<MailDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Mail> list = new ArrayList<Mail>( dtoList.size() );
        for ( MailDTO mailDTO : dtoList ) {
            list.add( toEntity( mailDTO ) );
        }

        return list;
    }

    @Override
    public List<MailDTO> toDto(List<Mail> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MailDTO> list = new ArrayList<MailDTO>( entityList.size() );
        for ( Mail mail : entityList ) {
            list.add( toDto( mail ) );
        }

        return list;
    }

    @Override
    public Set<Mail> toEntity(Set<MailDTO> dtoSet) {
        if ( dtoSet == null ) {
            return null;
        }

        Set<Mail> set = new LinkedHashSet<Mail>( Math.max( (int) ( dtoSet.size() / .75f ) + 1, 16 ) );
        for ( MailDTO mailDTO : dtoSet ) {
            set.add( toEntity( mailDTO ) );
        }

        return set;
    }

    @Override
    public Set<MailDTO> toDto(Set<Mail> entitySet) {
        if ( entitySet == null ) {
            return null;
        }

        Set<MailDTO> set = new LinkedHashSet<MailDTO>( Math.max( (int) ( entitySet.size() / .75f ) + 1, 16 ) );
        for ( Mail mail : entitySet ) {
            set.add( toDto( mail ) );
        }

        return set;
    }

    @Override
    public MailDTO toDto(Mail mail) {
        if ( mail == null ) {
            return null;
        }

        MailDTO.Builder mailDTO = MailDTO.builder();

        mailDTO.userId( mailUserId( mail ) );
        mailDTO.userEmail( mailUserEmail( mail ) );
        mailDTO.id( mail.getId() );
        mailDTO.title( mail.getTitle() );
        mailDTO.content( mail.getContent() );
        mailDTO.mailEvent( mail.getMailEvent() );
        mailDTO.language( mail.getLanguage() );
        mailDTO.createDatetime( mail.getCreateDatetime() );

        return mailDTO.build();
    }

    @Override
    public Mail toEntity(MailDTO mailDTO) {
        if ( mailDTO == null ) {
            return null;
        }

        Mail mail = new Mail();

        mail.setUser( userMapper.fromId( mailDTO.getUserId() ) );
        mail.setId( mailDTO.getId() );
        mail.setTitle( mailDTO.getTitle() );
        mail.setContent( mailDTO.getContent() );
        mail.setMailEvent( mailDTO.getMailEvent() );
        mail.setLanguage( mailDTO.getLanguage() );
        mail.setCreateDatetime( mailDTO.getCreateDatetime() );

        return mail;
    }

    private Long mailUserId(Mail mail) {
        if ( mail == null ) {
            return null;
        }
        User user = mail.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String mailUserEmail(Mail mail) {
        if ( mail == null ) {
            return null;
        }
        User user = mail.getUser();
        if ( user == null ) {
            return null;
        }
        String email = user.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }
}
