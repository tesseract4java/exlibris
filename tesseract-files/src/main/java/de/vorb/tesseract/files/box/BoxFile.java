package de.vorb.tesseract.files.box;

import com.google.common.collect.ImmutableList;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoxFile {

    private final List<BoxFileLine> lines;

    public static BoxFile ofLines(Iterable<BoxFileLine> lines) {
        checkNotNull(lines);
        return new BoxFile(ImmutableList.copyOf(lines));
    }

}
