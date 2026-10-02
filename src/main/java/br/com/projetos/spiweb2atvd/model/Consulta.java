package br.com.projetos.spiweb2atvd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_consulta")
@Getter
@Setter
@NoArgsConstructor
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime data;

    @Positive
    @DecimalMin("0.01")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal valor;

    @NotBlank
    @Pattern(regexp = "^[\\p{L}\\p{N}\\p{P}\\p{Z}]+$", message = "A observação contém caracteres inválidos")
    private String observacao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
}