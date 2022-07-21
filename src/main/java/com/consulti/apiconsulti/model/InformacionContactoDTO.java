
package com.consulti.apiconsulti.model;

        import java.util.List;
        import java.util.UUID;

        import com.consulti.apiconsulti.entity.DetalleResume;
        import com.consulti.apiconsulti.entity.InformacionContacto;
        import com.consulti.apiconsulti.entity.Label;

        import lombok.Data;

@Data
public class InformacionContactoDTO {
    private UUID user;
    private List<InformacionContacto> contacto;

}