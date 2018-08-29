package ro.esolutions.eipl.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.esolutions.eipl.types.MabecCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MabecCode mabecCode;

    @NotBlank
    private String protectionType;

    @NotBlank
    private String size;

    @NotBlank
    private String sex;
}
