package file;

import net.mamoe.mirai.console.data.Value;
import net.mamoe.mirai.console.data.java.JavaAutoSavePluginConfig;

public class AutoSaveConfigFile extends JavaAutoSavePluginConfig {
    public static final AutoSaveConfigFile INSTANCE = new AutoSaveConfigFile();
    public AutoSaveConfigFile(){
        super("CrazyThursdayPlugin");
    }
    public final Value<CrazyPluginConfig> content = typedValue("content", createKType(CrazyPluginConfig.class),new CrazyPluginConfig());
}
