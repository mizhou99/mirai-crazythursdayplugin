package command;

import file.CrazyPluginManager;
import mi.yxz.MiraiCrazyThursdayPlugin;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.MemberPermission;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

public class CrazyCommandManager {
    public ArrayList<String> commands = new ArrayList<>();

    public ArrayList<String> getCommands() {
        commands.add("指令列表");
        commands.add("subscribe | sub | 订阅");
        commands.add("unsub | unsubscribe | 退订");
        commands.add("gl | grouplist | 群列表");
        return commands;
    }

    public void manageMessage(long group, long qq, @NotNull MessageChain messageChain)
    {
        String msg = messageChain.contentToString();
        if(msg.length()==0){
            sendMessage(group,"文本长度为0");
        }
        else{
            if(verifyCommand(msg)){
                if(commandPermissionRunCheckup(group,qq))
                {
                    runCommand(group,msg);
                }
                else sendMessage(group,"只有群主和管理员拥有命令权限");
            }
        }
    }
    public void sendMessage(long group,String string)
    {
        Objects.requireNonNull(MiraiCrazyThursdayPlugin.getCurrent_bot().getGroup(group)).sendMessage(string);
    }
    public boolean verifyCommand(@NotNull String string) {
        // match $start $remove $set
        return string.matches("^\\$.*$");
    }
    public boolean commandPermissionRunCheckup(long group,long qq) {
        Member member;
        member = Objects.requireNonNull(MiraiCrazyThursdayPlugin.getCurrent_bot().getGroup(group)).get(qq);
        assert member != null;
        /*
        断言是个好东西
         */
        return member.getPermission() == MemberPermission.OWNER || member.getPermission() == MemberPermission.ADMINISTRATOR;
    }
    public void runCommand(long group, @NotNull String string){
        /*
         * 只是简单的用了正则表达式匹配了指令，之后用mirai的指令注册器试试！*/
        Pattern pattern = Pattern.compile("\\$sub|subscribe|订阅.*",Pattern.CASE_INSENSITIVE);
        if(pattern.matcher(string).matches()){
            if(CrazyPluginManager.active.contains(group)){
                sendMessage(group,"本群已经订阅");
            }
            else {
                CrazyPluginManager.active.add(group);
                sendMessage(group, "订阅成功");
            }
        } else if (string.matches("\\$(?i)unsub|unsubscribe|退订.*")) {
            CrazyPluginManager.active.remove(group);
            sendMessage(group,"退订成功");
        } else if (string.matches("\\$(?i)gl|grouplist|群列表.*")){
            StringBuilder str = new StringBuilder("已订阅群列表:").append("\n");
            for (Long groupList : CrazyPluginManager.active) {
                String groupName = Objects.requireNonNull(MiraiCrazyThursdayPlugin.getCurrent_bot().getGroup(groupList)).getName();
                str.append(groupName).append(" ").append(groupList).append("\n");
            }
            sendMessage(group, str.toString());
        } else if(string.matches("\\$(?i)help|帮助.*")) {
            StringBuilder str1 = new StringBuilder("帮助:").append("\n");
            for(String commandList: getCommands()){
                str1.append(commandList).append("\n");
            }
            sendMessage(group,str1.toString());
        }
        /*
          正则表达式不区分大小写:
          Pattern pattern = Pattern.compile("String.class",Pattern.CASE_INSENSITIVE)
          pattern.matcher(string).matches()==true|false
          OR
          string.matches("(?i)abc")==true|false
         */
    }

}
