package file;

import datas.DataManager;
import mi.yxz.MiraiCrazyThursdayPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CrazyPluginManager {
    public static List<Long> active = new ArrayList<>();
    public void readCrazyConfigByPluginAutoSave(){
        CrazyPluginConfig crazyPluginConfig = AutoSaveConfigFile.INSTANCE.content.get();
        readCrazyPluginConfig(crazyPluginConfig);
    }
    private void readCrazyPluginConfig(CrazyPluginConfig crazyPluginConfig){
        if(!crazyPluginConfig.getVersion().equals(MiraiCrazyThursdayPlugin.version)){
            System.out.println("配置文件过时，当前版本: " + MiraiCrazyThursdayPlugin.version+"请下载最新版本");
        }
        active = crazyPluginConfig.getActiveCrazyGroupList();
    }
    public static void sendCrazyToGroup(){
        File file = new File("data/mi.yxz.mirai-CrazyThursdayPlugin/thursday.txt");
        for (Long group: active) {
            try {
                String messageToSend = DataManager.sendCrazy(file);
                Objects.requireNonNull(MiraiCrazyThursdayPlugin.getCurrent_bot().getGroup(group)).sendMessage(messageToSend);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
