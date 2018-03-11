package de.vorb.tesseract.files.box;

import java.io.IOException;
import java.nio.file.Path;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class BoxFileParseException extends RuntimeException {

    BoxFileParseException(Path filePath, IOException e) {
        super("Unable to parse file at '" + checkNotNull(filePath) + "'", checkNotNull(e));
    }

    BoxFileParseException(int lineNumber) {
        super("Unable to read line number " + lineNumber);
        checkLineNumber(lineNumber);
    }

    BoxFileParseException(int lineNumber, String invalidLineFormat) {
        super("Unable to parse line number " + lineNumber + ", reason: " + checkNotNull(invalidLineFormat));
        checkLineNumber(lineNumber);
    }

    BoxFileParseException(int lineNumber, NumberFormatException e) {
        super("Unable to parse line number " + lineNumber, checkNotNull(e));
        checkLineNumber(lineNumber);
    }

    private void checkLineNumber(int lineNumber) {
        checkArgument(lineNumber >= 1, "lineNumber <= 0");
    }

}
