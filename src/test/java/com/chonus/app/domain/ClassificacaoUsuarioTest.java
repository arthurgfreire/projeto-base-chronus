package com.chonus.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.chonus.app.web.rest.TestUtil;

public class ClassificacaoUsuarioTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClassificacaoUsuario.class);
        ClassificacaoUsuario classificacaoUsuario1 = new ClassificacaoUsuario();
        classificacaoUsuario1.setId("id1");
        ClassificacaoUsuario classificacaoUsuario2 = new ClassificacaoUsuario();
        classificacaoUsuario2.setId(classificacaoUsuario1.getId());
        assertThat(classificacaoUsuario1).isEqualTo(classificacaoUsuario2);
        classificacaoUsuario2.setId("id2");
        assertThat(classificacaoUsuario1).isNotEqualTo(classificacaoUsuario2);
        classificacaoUsuario1.setId(null);
        assertThat(classificacaoUsuario1).isNotEqualTo(classificacaoUsuario2);
    }
}
