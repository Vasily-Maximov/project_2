package ru.yandex.practicum.filmorate.model;

import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.filmorate.validation.CreateGroup;
import ru.yandex.practicum.filmorate.validation.UpdateGroup;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Setter
@Getter
public class AbstractModel {

    @NotNull(groups = UpdateGroup.class)
    @Null(groups = CreateGroup.class)
    private Integer id;
    private ModelType modelType;
}