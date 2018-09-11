package ro.esolutions.eipl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.esolutions.eipl.types.MabecCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EquipmentModel {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotNull
    private MabecCode mabecCode;

    @NotBlank
    private String protectionType;

    @NotBlank
    private String size;

    @NotBlank
    private String sex;
}