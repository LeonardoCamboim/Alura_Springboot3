package med.voll.api.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;


@Entity(name = "Paciente")
@Table(name = "Pacientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());

    }

    public void pacienteAtualizarDados(DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }

    }
}
