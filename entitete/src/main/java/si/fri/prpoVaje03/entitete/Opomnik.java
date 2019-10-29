package si.fri.prpoVaje03.entitete;

import javax.persistence.*;

@Entity(name = "opomnik")
@NamedQueries(value =
        {
                @NamedQuery(name = "Opomnik.getAll", query = "SELECT o FROM opomnik o")
        })
public class Opomnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String naslov;

    private String opis;

    //@ManyToOne
    //@JoinColumn(name = "lokacija_id")
    //private Lokacija lokacija;

    // getter in setter metode

}
