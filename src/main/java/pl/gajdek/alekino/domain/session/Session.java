package pl.gajdek.alekino.domain.session;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "spring_session")
public class Session implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @Column(name = "SESSION_ID")
    private String primaryId;

//    @Column(name = "SESSION_ID")
    private String sessionId;

    private String creationTime;

    private String lastAccessTime;

    private String maxInactiveInterval;

    private String expiryTime;
    private String principalName;

 //   private String

//    @Column(name = "ATTRIBUTE_NAME")
//    private String attributeName;
//
//    @Column(name = "ATTRIBUTE_VALUE")
//    private byte[] attributeValue;

}
