package de.vorb.tesseract.files.box;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public interface BoxFileParser {

    BoxFile parseBoxFile(BufferedReader fileReader);

    default BoxFile parseBoxFile(Path filePath, Charset charset) {
        try (final BufferedReader bufferedReader = Files.newBufferedReader(filePath, charset)) {
            return parseBoxFile(bufferedReader);
        } catch (IOException e) {
            throw new BoxFileParseException(filePath, e);
        }
    }

}
