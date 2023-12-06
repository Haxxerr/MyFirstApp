package com.example.NewProject.service.mapper;

import com.example.NewProject.domain.ConfirmationToken;
import com.example.NewProject.domain.User;
import com.example.NewProject.service.dto.ConfirmationTokenDTO;
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
public class ConfirmationTokenMapperImpl implements ConfirmationTokenMapper {

    @Override
    public List<ConfirmationToken> toEntity(List<ConfirmationTokenDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ConfirmationToken> list = new ArrayList<ConfirmationToken>( dtoList.size() );
        for ( ConfirmationTokenDTO confirmationTokenDTO : dtoList ) {
            list.add( toEntity( confirmationTokenDTO ) );
        }

        return list;
    }

    @Override
    public List<ConfirmationTokenDTO> toDto(List<ConfirmationToken> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ConfirmationTokenDTO> list = new ArrayList<ConfirmationTokenDTO>( entityList.size() );
        for ( ConfirmationToken confirmationToken : entityList ) {
            list.add( toDto( confirmationToken ) );
        }

        return list;
    }

    @Override
    public Set<ConfirmationToken> toEntity(Set<ConfirmationTokenDTO> dtoSet) {
        if ( dtoSet == null ) {
            return null;
        }

        Set<ConfirmationToken> set = new LinkedHashSet<ConfirmationToken>( Math.max( (int) ( dtoSet.size() / .75f ) + 1, 16 ) );
        for ( ConfirmationTokenDTO confirmationTokenDTO : dtoSet ) {
            set.add( toEntity( confirmationTokenDTO ) );
        }

        return set;
    }

    @Override
    public Set<ConfirmationTokenDTO> toDto(Set<ConfirmationToken> entitySet) {
        if ( entitySet == null ) {
            return null;
        }

        Set<ConfirmationTokenDTO> set = new LinkedHashSet<ConfirmationTokenDTO>( Math.max( (int) ( entitySet.size() / .75f ) + 1, 16 ) );
        for ( ConfirmationToken confirmationToken : entitySet ) {
            set.add( toDto( confirmationToken ) );
        }

        return set;
    }

    @Override
    public ConfirmationTokenDTO toDto(ConfirmationToken confirmationToken) {
        if ( confirmationToken == null ) {
            return null;
        }

        ConfirmationTokenDTO.Builder confirmationTokenDTO = ConfirmationTokenDTO.builder();

        confirmationTokenDTO.userId( confirmationTokenUserId( confirmationToken ) );
        confirmationTokenDTO.email( confirmationTokenUserEmail( confirmationToken ) );
        confirmationTokenDTO.userName( confirmationTokenUserUsername( confirmationToken ) );
        confirmationTokenDTO.id( confirmationToken.getId() );
        confirmationTokenDTO.confirmationToken( confirmationToken.getConfirmationToken() );
        confirmationTokenDTO.createDate( confirmationToken.getCreateDate() );
        confirmationTokenDTO.expireDate( confirmationToken.getExpireDate() );
        confirmationTokenDTO.confirmDate( confirmationToken.getConfirmDate() );

        return confirmationTokenDTO.build();
    }

    @Override
    public ConfirmationToken toEntity(ConfirmationTokenDTO confirmationTokenDTO) {
        if ( confirmationTokenDTO == null ) {
            return null;
        }

        ConfirmationToken confirmationToken = new ConfirmationToken();

        confirmationToken.setUser( confirmationTokenDTOToUser( confirmationTokenDTO ) );
        confirmationToken.setId( confirmationTokenDTO.getId() );
        confirmationToken.setConfirmationToken( confirmationTokenDTO.getConfirmationToken() );
        confirmationToken.setCreateDate( confirmationTokenDTO.getCreateDate() );
        confirmationToken.setExpireDate( confirmationTokenDTO.getExpireDate() );
        confirmationToken.setConfirmDate( confirmationTokenDTO.getConfirmDate() );

        return confirmationToken;
    }

    private Long confirmationTokenUserId(ConfirmationToken confirmationToken) {
        if ( confirmationToken == null ) {
            return null;
        }
        User user = confirmationToken.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String confirmationTokenUserEmail(ConfirmationToken confirmationToken) {
        if ( confirmationToken == null ) {
            return null;
        }
        User user = confirmationToken.getUser();
        if ( user == null ) {
            return null;
        }
        String email = user.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private String confirmationTokenUserUsername(ConfirmationToken confirmationToken) {
        if ( confirmationToken == null ) {
            return null;
        }
        User user = confirmationToken.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    protected User confirmationTokenDTOToUser(ConfirmationTokenDTO confirmationTokenDTO) {
        if ( confirmationTokenDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( confirmationTokenDTO.getUserId() );
        user.setEmail( confirmationTokenDTO.getEmail() );
        user.setUsername( confirmationTokenDTO.getUserName() );

        return user;
    }
}
