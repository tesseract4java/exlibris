package de.vorb.tesseract.files.box;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.StringTokenizer;

import static com.google.common.base.Preconditions.checkNotNull;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoxFileLine {

    private static final String TOKEN_DELIMITER = " ";

    private final String glyph;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final int pageIndex;

    public String asStringLine() {
        return glyph + ' ' + x + ' ' + y + ' ' + width + ' ' + height + ' ' + pageIndex;
    }

    public static BoxFileLine of(String glyph, int x, int y, int width, int height, int pageIndex) {
        checkNotNull(glyph);
        return new BoxFileLine(glyph, x, y, width, height, pageIndex);
    }

    public static BoxFileLine fromStringLine(String line) {
        checkNotNull(line);

        final StringTokenizer tokenizer = new StringTokenizer(line, TOKEN_DELIMITER);

        final String glyph = tokenizer.nextToken();
        final int x = readNextInt(tokenizer);
        final int y = readNextInt(tokenizer);
        final int width = readNextInt(tokenizer);
        final int height = readNextInt(tokenizer);
        final int pageIndex = readNextInt(tokenizer);

        return BoxFileLine.of(glyph, x, y, width, height, pageIndex);
    }

    private static int readNextInt(StringTokenizer tokenizer) {
        return Integer.valueOf(tokenizer.nextToken());
    }

}
