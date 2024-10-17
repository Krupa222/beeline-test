package org.test.entity.dto;

import lombok.Getter;
import lombok.Setter;
import org.test.entity.Client;
import org.test.entity.MenuButtons;
import org.test.entity.Setting;

import java.util.List;

@Getter
@Setter
public class JsonDTO {
    Client client;
    Setting setting;
    List<MenuButtons> menuButtons;
}
