package pl.gajdek.alekino.domain.session;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "SPRING_SESSION_ATTRIBUTES")
@Getter
@Setter
public class SessionAttributes implements Serializable {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "SESSION_PRIMARY_ID")
        private String sessionPrimaryId;

        @Column(name = "ATTRIBUTE_NAME")
        private String attributeName;

        @Column(name = "ATTRIBUTE_BYTES", length = 4000)
        private byte[] attributeBytes;

}
