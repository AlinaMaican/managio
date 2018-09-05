package ro.esolutions.eipl.entities;

import lombok.*;
import ro.esolutions.eipl.types.MabecCode;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "equipments",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "code")
        }
)

public class Equipment {

    @Id
    @GeneratedValue(generator = "EQUIPMENTS_GEN_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "EQUIPMENTS_GEN_SEQ", sequenceName = "equipment_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String code;

    @Enumerated(EnumType.STRING)
    private MabecCode mabecCode;

    private String protectionType;

    private String size;

    private String sex;

    private Boolean isAvailable;
}
