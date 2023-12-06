package com.example.NewProject.service.mapper;

import com.example.NewProject.domain.Role;
import com.example.NewProject.service.dto.RoleDTO;
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
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toEntity(RoleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( dto.getId() );
        role.setName( dto.getName() );

        return role;
    }

    @Override
    public RoleDTO toDto(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDTO.Builder roleDTO = RoleDTO.builder();

        if ( entity.getId() != null ) {
            roleDTO.id( entity.getId() );
        }
        roleDTO.name( entity.getName() );

        return roleDTO.build();
    }

    @Override
    public List<Role> toEntity(List<RoleDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( dtoList.size() );
        for ( RoleDTO roleDTO : dtoList ) {
            list.add( toEntity( roleDTO ) );
        }

        return list;
    }

    @Override
    public List<RoleDTO> toDto(List<Role> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RoleDTO> list = new ArrayList<RoleDTO>( entityList.size() );
        for ( Role role : entityList ) {
            list.add( toDto( role ) );
        }

        return list;
    }

    @Override
    public Set<Role> toEntity(Set<RoleDTO> dtoSet) {
        if ( dtoSet == null ) {
            return null;
        }

        Set<Role> set = new LinkedHashSet<Role>( Math.max( (int) ( dtoSet.size() / .75f ) + 1, 16 ) );
        for ( RoleDTO roleDTO : dtoSet ) {
            set.add( toEntity( roleDTO ) );
        }

        return set;
    }

    @Override
    public Set<RoleDTO> toDto(Set<Role> entitySet) {
        if ( entitySet == null ) {
            return null;
        }

        Set<RoleDTO> set = new LinkedHashSet<RoleDTO>( Math.max( (int) ( entitySet.size() / .75f ) + 1, 16 ) );
        for ( Role role : entitySet ) {
            set.add( toDto( role ) );
        }

        return set;
    }
}
