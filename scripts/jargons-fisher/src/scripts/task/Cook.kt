package scripts.task

import org.tribot.script.sdk.Inventory
import org.tribot.script.sdk.MyPlayer
import org.tribot.script.sdk.Waiting
import org.tribot.script.sdk.Widgets
import org.tribot.script.sdk.query.Query
import org.tribot.script.sdk.walking.GlobalWalking
import scripts.data.Area.Companion.COOKING_SPOT_AREA
import scripts.data.Var.Companion.rawFishList

class Cook {

    companion object {

        fun validate(): Boolean { return Inventory.isFull() && Inventory.contains(rawFishList[0], rawFishList[1])}

        fun cook() {
            if(!COOKING_SPOT_AREA.containsMyPlayer()) { GlobalWalking.walkTo(COOKING_SPOT_AREA.randomTile) }
            while(Inventory.contains(rawFishList[0], rawFishList[1])) {
                    while (!Query.widgets().inRoots(270).textEquals("What would you like to cook?").isAny &&
                        !Query.widgets().inRoots(270).textEquals("How many would you like to cook?").isAny
                    ) {
                        if (Query.gameObjects().nameEquals("Range").isAny) {
                            Query.gameObjects().nameEquals("Range").findClosestByPathDistance().get().interact("Cook")
                            Waiting.waitUntilAnimating(1500)
                            while (MyPlayer.isAnimating() || MyPlayer.isMoving()) {
                                Waiting.waitNormal(100, 800)
                            }
                        }
                    }
                Widgets.findWhereAction("Cook").get().click()
                Waiting.waitUntilAnimating(3000)
                while (MyPlayer.isAnimating() || MyPlayer.isMoving()) {
                    Waiting.waitNormal(100, 1600)
                }
            }

        }

    }

}