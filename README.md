## mirai-CrazyThursdayPlugin
基于**mirai-console**的插件，在每周四的**8:00~20:00**, 每逢整点推送疯狂星期四段子。

灵感来自[肯德基疯狂星期四! 推送插件](https://github.com/KuriYama-mirai524/KFC-creazy-Thursday)。

学习mirai的api的过程中参考了[Petpet](https://github.com/Dituon/petpet)。大佬的代码真是百看不厌(膜拜)

使用java编写，使用了**quartz**实现定时任务。

***

## 使用方法

1.下载release版本的插件jar包。

2.放入 `plugin` 文件夹中。

3.启动mirai-console，完成data初始化。

4.在 `data/mi.yxz.mirai-CrazyThursdayPlugin/thursday.txt` 中放入你自己喜欢的段子，一个段子占一行(**记得把生成的提示删掉**)。

5.重启mirai-console

***

## 配置文件

运行后生成 `config/mi.yxz.mirai-CrazyThursdayPlugin/CrazyThursdayPlugin.yml` 文件

**(其实也没什么可配置的)**

```
content: 
  version: 0.1.0 #版本号
  activeCrazyGroupList: [] #订阅群列表
```
>可以直接在 `订阅群列表` 添加群。下次启动生效。

***

## 指令

**指令头** `$`

**订阅** `sub|subscribe|订阅`

**退订** `unsub|unsubscribe|退订`

**查看群列表** `gl|grouplist|群列表`

**查看帮助** `help|帮助`

`PS:不区分大小写`

***

## 权限

>为了不想让订阅和退订太频繁(群友整活)，只有群主和管理员有命令权限。


