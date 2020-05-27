package com.chonus.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.chonus.app.web.rest.TestUtil;

public class EnderecoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnderecoDTO.class);
        EnderecoDTO enderecoDTO1 = new EnderecoDTO();
        enderecoDTO1.setId("id1");
        EnderecoDTO enderecoDTO2 = new EnderecoDTO();
        assertThat(enderecoDTO1).isNotEqualTo(enderecoDTO2);
        enderecoDTO2.setId(enderecoDTO1.getId());
        assertThat(enderecoDTO1).isEqualTo(enderecoDTO2);
        enderecoDTO2.setId("id2");
        assertThat(enderecoDTO1).isNotEqualTo(enderecoDTO2);
        enderecoDTO1.setId(null);
        assertThat(enderecoDTO1).isNotEqualTo(enderecoDTO2);
    }
}
