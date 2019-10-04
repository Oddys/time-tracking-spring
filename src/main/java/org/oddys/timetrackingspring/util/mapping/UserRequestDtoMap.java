package org.oddys.timetrackingspring.util.mapping;

import lombok.AllArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.oddys.timetrackingspring.dto.UserRequestDto;
import org.oddys.timetrackingspring.persist.entity.Role;
import org.oddys.timetrackingspring.persist.entity.User;
import org.oddys.timetrackingspring.util.PasswordManager;

@AllArgsConstructor
public class UserRequestDtoMap extends PropertyMap<UserRequestDto, User> {
    private final PasswordManager passwordManager;

    @Override
    protected void configure() {
        Converter<String, String> hash = new AbstractConverter<>() {
            @Override
            protected String convert(String s) {
                return passwordManager.hashPassword(s);
            }
        };
        using(hash).map().setPassword(source.getPassword());
    }
}
