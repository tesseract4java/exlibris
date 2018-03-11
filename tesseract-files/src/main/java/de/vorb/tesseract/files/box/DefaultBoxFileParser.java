package de.vorb.tesseract.files.box;

import org.apache.logging.log4j.util.Strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class DefaultBoxFileParser implements BoxFileParser {

    @Override
    public BoxFile parseBoxFile(BufferedReader fileReader) {
        final List<BoxFileLine> lines = new ArrayList<>();
        String line;

        try {
            while ((line = fileReader.readLine()) != null) {
                if (Strings.isBlank(line)) {
                    continue;
                }
                try {
                    lines.add(BoxFileLine.fromStringLine(line));
                } catch (NoSuchElementException e) {
                    throw new BoxFileParseException(lines.size() + 1, "invalid line format");
                } catch (NumberFormatException e) {
                    throw new BoxFileParseException(lines.size() + 1, e);
                }
            }
        } catch (IOException e) {
            throw new BoxFileParseException(lines.size() + 1);
        }

        return BoxFile.ofLines(lines);
    }

}
