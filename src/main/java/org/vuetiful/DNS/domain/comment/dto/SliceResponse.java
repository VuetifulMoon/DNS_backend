package org.vuetiful.DNS.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
@AllArgsConstructor
public class SliceResponse<T> {
    private final List<T> content;
    private final int pageNumber;
    private final int size;
    private final boolean first;
    private final boolean last;

    public SliceResponse(Slice<T> slice) {
        this.content = slice.getContent();
        this.pageNumber = slice.getNumber();
        this.size = slice.getSize();
        this.first = slice.isFirst();
        this.last = slice.isLast();
    }
}