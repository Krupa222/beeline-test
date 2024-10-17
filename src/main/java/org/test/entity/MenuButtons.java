package org.test.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuButtons {
    private String mark;
    private String name;
    private String message;
    private MenuButtons prevButton;
    private List<MenuButtons> nextButtons;
}
