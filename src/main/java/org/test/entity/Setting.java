package org.test.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Setting {
    private String mainMenu;
    private String prevMenu;
    private String curMsg;
    private Long balanceLimit;
    private String balanceLogic;
}
