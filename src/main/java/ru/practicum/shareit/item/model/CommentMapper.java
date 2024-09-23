package ru.practicum.shareit.item.model;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.item.dto.CommentAddRequestDto;
import ru.practicum.shareit.item.dto.CommentInfoResponseDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@UtilityClass
public class CommentMapper {

    public Comment toModel(final User user, final Item item, final CommentAddRequestDto commentAddRequestDto) {
        return Comment.builder()
                .text(commentAddRequestDto.getText())
                .item(item)
                .author(user)
                .build();
    }


    public CommentInfoResponseDto toDto(final Comment comment) {
        return CommentInfoResponseDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .authorName(comment.getAuthor().getName())
                .created(comment.getCreated())
                .build();
    }


    public List<CommentInfoResponseDto> toDto(final List<Comment> comments) {
        return comments.stream()
                .map(CommentMapper::toDto)
                .toList();
    }
}
