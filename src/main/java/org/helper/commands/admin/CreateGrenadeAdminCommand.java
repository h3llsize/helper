package org.helper.commands.admin;

import org.helper.impl.IAdmin;
import org.helper.user.User;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CreateGrenadeAdminCommand extends IAdmin {
    /**
     * С помощью этого, мы получаем имя действия
     * Каждое действие будет содержать своё имя и тип(нажал на кнопку, тыкнул на эмодзи и т.д)
     * После того, как наш пользователь сделает действие, мы будем его сравнивать с имеющимися
     *
     * @param name команды
     */
    public CreateGrenadeAdminCommand(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, User user) {
    }
}
