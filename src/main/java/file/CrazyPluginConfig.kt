package file

import kotlinx.serialization.Serializable
import mi.yxz.MiraiCrazyThursdayPlugin

@Serializable
data class CrazyPluginConfig (
    val version: String = MiraiCrazyThursdayPlugin.version,
    val activeCrazyGroupList: List<Long> = emptyList()
)