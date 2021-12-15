package com.epam.esm.service.util;

import com.epam.esm.database.entity.Tag;
import com.epam.esm.service.dto.TagDto;

import java.util.List;
import java.util.stream.Collectors;

public class TagDtoMapper {

    public static Tag toObject(TagDto tagDto) {
        return new Tag(tagDto.getId(), tagDto.getName());
    }

    public static List<Tag> toObject(List<TagDto> tagsDto) {
        return tagsDto.stream().map((TagDtoMapper::toObject)).collect(Collectors.toList());
    }

    public static TagDto toDto(Tag tag) {
        return new TagDto(tag.getId(), tag.getName());
    }

    public static List<TagDto> toDto(List<Tag> tags) {
        return tags.stream().map((TagDtoMapper::toDto)).collect(Collectors.toList());
    }


}
