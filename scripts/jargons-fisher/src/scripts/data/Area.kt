package scripts.data

import org.tribot.script.sdk.types.Area
import org.tribot.script.sdk.types.WorldTile

class Area {

    companion object {

        val FISHING_SPOT_AREA = Area.fromRadius(WorldTile(3270, 3146, 0), 5)
        val COOKING_SPOT_AREA = Area.fromRadius(WorldTile(3274, 3177, 0), 8)
        val AL_KHARID_BANK_TILE = WorldTile(3272, 3167, 0)

    }

}