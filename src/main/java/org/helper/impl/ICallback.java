package org.helper.impl;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.helper.commands.launcher.ActionStorage;

public abstract class ICallback {
    public String name;
    public ICallback(String name)
    {
        this.name = name;
        initialize();
    }
    public abstract void exec(Update action, IUser user);
    public void initialize()
    {
        ActionStorage.addCallback(this, this.name);
    }
}
