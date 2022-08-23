package mi.yxz;

import command.CrazyCommandManager;
import datas.DataManager;
import file.AutoSaveConfigFile;
import file.CrazyPluginManager;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotOnlineEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import time.CrazyTime;

public final class MiraiCrazyThursdayPlugin extends JavaPlugin {
    public static final MiraiCrazyThursdayPlugin INSTANCE = new MiraiCrazyThursdayPlugin();
    private static Bot Current_bot = null;
    public static String version = "0.1.0";
    public static String getVersion(){return version;}
    public static MiraiCrazyThursdayPlugin getInstance(){return INSTANCE;}
    public static Bot getCurrent_bot(){return Current_bot;}
    public static CrazyCommandManager commandManager = null;
    public static CrazyPluginManager crazyPluginManager;
    private MiraiCrazyThursdayPlugin() {
        super(new JvmPluginDescriptionBuilder("mi.yxz.mirai-CrazyThursdayPlugin", "0.1.0")
                .name("mirai-CrazyThursdayPlugin")
                .author("mizhou")
                .build());
        crazyPluginManager = new CrazyPluginManager();
    }

    @Override
    public void onEnable() {
        getLogger().info("CrazyThursday Plugin loaded!");
        commandManager = new CrazyCommandManager();
        try {
            this.reloadPluginConfig(AutoSaveConfigFile.INSTANCE);
            crazyPluginManager.readCrazyConfigByPluginAutoSave();
        }catch (NoClassDefFoundError ignored){
            getLogger().error("Mirai 2.11.0 提供了新的 JavaAutoSaveConfig 方法, 请更新Mirai版本至 2.11.0 (不是2.11.0-M1)\n使用旧版本将无法配置config");
        }
        GlobalEventChannel.INSTANCE.subscribeAlways(BotOnlineEvent.class,
                event -> tryInitializeBot(event.getBot()));
        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class,
                event -> commandManager.manageMessage(event.getGroup().getId(), event.getSender().getId(),
                        event.getMessage()));
        try{
            if(DataManager.checkCrazyFile()){
                getLogger().info("thursday.txt已存在，请检查段子是否到位，然后开启疯狂星期四吧！！！");
                CrazyTime.sendCrazyTime();
            }
            else {
                DataManager.createCrazy();
                getLogger().info("初次启动，请检查data目录，用自己的段子填入thursday.txt，并重启mirai-console");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void tryInitializeBot(Bot bot){
        if(Current_bot == null){
            Current_bot = bot;
        }
    }
}