package org.helper.parser.grenades;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;

public class Grenade {
    private String map;
    private String desc;
    private String name;
    private InputFile video;

    public void setMap(String map) {
        this.map = map;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVideo(InputFile video) {
        this.video = video;
    }
}
