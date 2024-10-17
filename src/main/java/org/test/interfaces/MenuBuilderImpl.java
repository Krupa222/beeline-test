package org.test.interfaces;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
import org.test.entity.MenuButtons;
import org.test.entity.dto.JsonDTO;

@Getter
@Setter
public class MenuBuilderImpl implements MenuBuilder {

    private static Stack<MenuButtons> menuHistory = new Stack<>();
    private static JsonDTO json;
    private MenuButtons curBtn;
    private String msg = "";

    public MenuBuilderImpl(JsonDTO jsonDTO){
        json = jsonDTO;
        initMenuHistory();
        buildMenu(jsonDTO.getMenuButtons());
        System.out.println(jsonDTO.getClient().getStartMsg());
        System.out.println(msg);
    }

    private void initMenuHistory(){
        menuHistory.clear();
        menuHistory.push(new MenuButtons("", "", "", null, json.getMenuButtons()));
    }

    private String buildMenu(List<MenuButtons> menuButtons) {
        for (MenuButtons button: menuButtons){
            msg = msg.concat(button.getMark() + " " + button.getName() + "\n");
        }
        msg = msg.concat(json.getSetting().getPrevMenu() + " Назад.\n");
        msg = msg.concat(json.getSetting().getMainMenu() + " Главное меню.\n");
        msg = msg.concat(json.getSetting().getCurMsg() + " Повторить сообщение.\n");
        return msg;
    }

    @Override
    public String tapButton(String action) {
        if (action.equals(json.getSetting().getCurMsg())){
            return msg;
        }
        msg = "";
        if (action.equals(json.getSetting().getMainMenu())){
            initMenuHistory();
            msg = buildMenu(json.getMenuButtons());
            return msg;
        } else if (action.equals(json.getSetting().getPrevMenu())){
            if (menuHistory.size() != 1) {
                menuHistory.pop();
            }
            msg = buildMenu(menuHistory.lastElement().getNextButtons());
            return msg;
        }
        Optional<MenuButtons> optionalMenuButton = menuHistory.lastElement()
                .getNextButtons()
                .stream()
                .filter(p -> action.equals(p.getMark())).findFirst();
        if (optionalMenuButton.isEmpty()){
            msg = "Нет соответсвий с пунктами меню, попробуйте ещё раз.\n" + buildMenu(new ArrayList<MenuButtons>());
        } else if (optionalMenuButton.get().getNextButtons().isEmpty()){
            if (optionalMenuButton.get().getName().equals("Баланс")){
                msg = json.getClient().getBalanceMsg(json.getSetting()).concat("\n");
            } else if (optionalMenuButton.get().getName().equals("Интернет трафик")){
                msg = json.getClient().getTrafficMsg().concat("\n");
            } else {
                msg = optionalMenuButton.get().getMessage().concat("\n");
            }
            menuHistory.push(optionalMenuButton.get());
            buildMenu(new ArrayList<MenuButtons>());
        } else {
            buildMenu(optionalMenuButton.get().getNextButtons());
            menuHistory.push(optionalMenuButton.get());
        }

        return msg;
    }
}
