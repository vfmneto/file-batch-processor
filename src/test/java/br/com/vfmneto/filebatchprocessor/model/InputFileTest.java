package br.com.vfmneto.filebatchprocessor.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture.SAMPLE_FILENAME;
import static org.assertj.core.api.Assertions.assertThat;

class InputFileTest {

    private InputFile inputFile;

    @Test
    @DisplayName("Given that the file extension is invalid it should return true")
    void shouldReturnTrueWhenExtensionIsInvalid() {
        inputFile = new InputFile(Path.of("invalid_extension.docx"));
        assertThat(inputFile.isInvalidExtension()).isTrue();
    }

    @Test
    @DisplayName("Given that the file extension is valid it should return false")
    void shouldReturnFalseWhenExtensionIsIvalid() {
        inputFile = new InputFile(Path.of(SAMPLE_FILENAME));
        assertThat(inputFile.isInvalidExtension()).isFalse();
    }

}
