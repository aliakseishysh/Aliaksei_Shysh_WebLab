package com.epam.esm.service.util;

import com.epam.esm.database.entity.Tag;
import com.epam.esm.database.entity.TagCost;
import com.epam.esm.service.dto.tag.TagCostDto;
import com.epam.esm.service.dto.tag.TagDto;
import com.epam.esm.service.dto.tag.CreateTagDto;
import com.epam.esm.service.dto.tag.ReadTagByNameDto;

import java.util.List;
import java.util.stream.Collectors;

public class TagMapper {

    public static Tag toObject(TagDto tagDto) {
        return new Tag(tagDto.getId(), tagDto.getName());
    }

    public static ReadTagByNameDto toDtoFromDto(CreateTagDto createTagDto) {
        return new ReadTagByNameDto(createTagDto.getName());
    }

    public static TagDto toDto(Tag tag) {
        return new TagDto(tag.getId(), tag.getName());
    }

    public static TagCostDto toDto(TagCost tagCost) {
        return new TagCostDto(TagMapper.toDto(tagCost.getTag()), tagCost.getCost());
    }

    public static TagDto toDto(CreateTagDto createTagDto) {
        return new TagDto(null, createTagDto.getName());
    }

    public static Tag toObject(CreateTagDto createTagDto) {
        return new Tag(null, createTagDto.getName());
    }

    public static List<TagDto> toDto(List<Tag> tags) {
        return tags.stream().map((TagMapper::toDto)).collect(Collectors.toList());
    }

    public static List<TagCostDto> toDtoTagCost(List<TagCost> tags) {
        return tags.stream().map((TagMapper::toDto)).collect(Collectors.toList());
    }

    public static List<Tag> toObject(List<TagDto> tagsDto) {
        return tagsDto.stream().map((TagMapper::toObject)).collect(Collectors.toList());
    }


}
