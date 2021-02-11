package pl.wegner.documents.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wegner.documents.model.enums.SendMethod;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Proof {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String designation;

    private int quantity;

    private String principal;

    private String dimension;

    @Enumerated(EnumType.ORDINAL)
    private SendMethod sendMethod;

    private String payer;
}
