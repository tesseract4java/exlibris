package de.vorb.tesseract.files.box;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class DefaultBoxFileParserTest {

    private final DefaultBoxFileParser defaultBoxFileParser = new DefaultBoxFileParser();

    @Test
    public void parsesValidBoxFile() {
        final BufferedReader reader = createReader("st 0 0 5 5 0\ni 10 0 5 5 0\n");
        final BoxFile boxFile = defaultBoxFileParser.parseBoxFile(reader);
        assertThat(boxFile.getLines()).containsExactly(
                BoxFileLine.of("st", 0, 0, 5, 5, 0),
                BoxFileLine.of("i", 10, 0, 5, 5, 0)
        );
    }

    @Test
    public void failsOnBoxFileWithMissingPageIndexes() {

        final BufferedReader reader = createReader("st 0 0 5 5 0\ni 10 0 5 5");

        assertThatExceptionOfType(BoxFileParseException.class)
                .isThrownBy(() -> defaultBoxFileParser.parseBoxFile(reader))
                .withMessageContaining("line number 2")
                .withMessageContaining("invalid line format");
    }

    @Test
    public void failsOnBoxFileWithInvalidCoordinate() {
        final BufferedReader reader = createReader("st 0 0 5.0 5 0\ni 10 0 5 5 0");
        assertThatExceptionOfType(BoxFileParseException.class)
                .isThrownBy(() -> defaultBoxFileParser.parseBoxFile(reader))
                .withMessageContaining("line number 1")
                .withCauseInstanceOf(NumberFormatException.class);
    }

    private BufferedReader createReader(String contents) {
        return new BufferedReader(new StringReader(contents));
    }
}
