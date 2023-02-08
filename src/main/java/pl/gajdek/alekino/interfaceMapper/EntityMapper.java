package pl.gajdek.alekino.interfaceMapper;

import org.springframework.stereotype.Component;

@Component
public interface EntityMapper <E,D>{

   E toEntity(D d);

    D toDto(E e);
}
