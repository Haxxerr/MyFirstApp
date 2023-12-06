package com.example.NewProject.service.mapper;

import com.example.NewProject.domain.PasswordResetToken;
import com.example.NewProject.domain.User;
import com.example.NewProject.service.dto.PasswordResetTokenDTO;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-06T18:31:01+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class PasswordResetTokenMapperImpl implements PasswordResetTokenMapper {

    @Override
    public List<PasswordResetToken> toEntity(List<PasswordResetTokenDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<PasswordResetToken> list = new ArrayList<PasswordResetToken>( dtoList.size() );
        for ( PasswordResetTokenDTO passwordResetTokenDTO : dtoList ) {
            list.add( toEntity( passwordResetTokenDTO ) );
        }

        return list;
    }

    @Override
    public List<PasswordResetTokenDTO> toDto(List<PasswordResetToken> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PasswordResetTokenDTO> list = new ArrayList<PasswordResetTokenDTO>( entityList.size() );
        for ( PasswordResetToken passwordResetToken : entityList ) {
            list.add( toDto( passwordResetToken ) );
        }

        return list;
    }

    @Override
    public Set<PasswordResetToken> toEntity(Set<PasswordResetTokenDTO> dtoSet) {
        if ( dtoSet == null ) {
            return null;
        }

        Set<PasswordResetToken> set = new LinkedHashSet<PasswordResetToken>( Math.max( (int) ( dtoSet.size() / .75f ) + 1, 16 ) );
        for ( PasswordResetTokenDTO passwordResetTokenDTO : dtoSet ) {
            set.add( toEntity( passwordResetTokenDTO ) );
        }

        return set;
    }

    @Override
    public Set<PasswordResetTokenDTO> toDto(Set<PasswordResetToken> entitySet) {
        if ( entitySet == null ) {
            return null;
        }

        Set<PasswordResetTokenDTO> set = new LinkedHashSet<PasswordResetTokenDTO>( Math.max( (int) ( entitySet.size() / .75f ) + 1, 16 ) );
        for ( PasswordResetToken passwordResetToken : entitySet ) {
            set.add( toDto( passwordResetToken ) );
        }

        return set;
    }

    @Override
    public PasswordResetTokenDTO toDto(PasswordResetToken passwordResetToken) {
        if ( passwordResetToken == null ) {
            return null;
        }

        PasswordResetTokenDTO.Builder passwordResetTokenDTO = PasswordResetTokenDTO.builder();

        passwordResetTokenDTO.userId( passwordResetTokenUserId( passwordResetToken ) );
        passwordResetTokenDTO.email( passwordResetTokenUserEmail( passwordResetToken ) );
        passwordResetTokenDTO.userName( passwordResetTokenUserUsername( passwordResetToken ) );
        passwordResetTokenDTO.id( passwordResetToken.getId() );
        passwordResetTokenDTO.passwordResetToken( passwordResetToken.getPasswordResetToken() );
        passwordResetTokenDTO.createDate( passwordResetToken.getCreateDate() );
        passwordResetTokenDTO.expireDate( passwordResetToken.getExpireDate() );
        passwordResetTokenDTO.confirmDate( passwordResetToken.getConfirmDate() );

        return passwordResetTokenDTO.build();
    }

    @Override
    public PasswordResetToken toEntity(PasswordResetTokenDTO passwordResetTokenDTO) {
        if ( passwordResetTokenDTO == null ) {
            return null;
        }

        PasswordResetToken passwordResetToken = new PasswordResetToken();

        passwordResetToken.setUser( passwordResetTokenDTOToUser( passwordResetTokenDTO ) );
        passwordResetToken.setId( passwordResetTokenDTO.getId() );
        passwordResetToken.setPasswordResetToken( passwordResetTokenDTO.getPasswordResetToken() );
        passwordResetToken.setCreateDate( passwordResetTokenDTO.getCreateDate() );
        passwordResetToken.setExpireDate( passwordResetTokenDTO.getExpireDate() );
        passwordResetToken.setConfirmDate( passwordResetTokenDTO.getConfirmDate() );

        return passwordResetToken;
    }

    private Long passwordResetTokenUserId(PasswordResetToken passwordResetToken) {
        if ( passwordResetToken == null ) {
            return null;
        }
        User user = passwordResetToken.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String passwordResetTokenUserEmail(PasswordResetToken passwordResetToken) {
        if ( passwordResetToken == null ) {
            return null;
        }
        User user = passwordResetToken.getUser();
        if ( user == null ) {
            return null;
        }
        String email = user.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private String passwordResetTokenUserUsername(PasswordResetToken passwordResetToken) {
        if ( passwordResetToken == null ) {
            return null;
        }
        User user = passwordResetToken.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    protected User passwordResetTokenDTOToUser(PasswordResetTokenDTO passwordResetTokenDTO) {
        if ( passwordResetTokenDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( passwordResetTokenDTO.getUserId() );
        user.setEmail( passwordResetTokenDTO.getEmail() );
        user.setUsername( passwordResetTokenDTO.getUserName() );

        return user;
    }
}
