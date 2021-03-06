package ro.esolutions.eipl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.esolutions.eipl.types.MabecCode;
import ro.esolutions.eipl.types.ProtectionType;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EquipmentModel {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String code;

    @NotNull
    private MabecCode mabecCode;

    private ProtectionType protectionType;
    @NotNull
    private String size;
    @NotNull
    private String sex;

    private LocalDate expirationDate;
}