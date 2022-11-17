package scripts.task

import org.tribot.script.sdk.Inventory
import org.tribot.script.sdk.MyPlayer
import org.tribot.script.sdk.Waiting
import org.tribot.script.sdk.query.Query
import org.tribot.script.sdk.walking.GlobalWalking
import scripts.data.Area.Companion.FISHING_SPOT_AREA
import scripts.data.Var.Companion.fishingEquipment

class Fish {

    companion object {

        fun validate(): Boolean {
            return(!Inventory.isFull() && Inventory.contains(fishingEquipment))
        }

        private fun clickFishingSpot(): Boolean {
            return if(Query.npcs().nameEquals("Fishing spot").findBestInteractable().isPresent) {
                Query.npcs().nameEquals("Fishing spot").findBestInteractable().get().interact("Small Net")
                Waiting.waitUntilAnimating(5000)
                while (MyPlayer.isAnimating()) { Waiting.waitNormal(2000, 20000) }
                true
            } else { false }
        }

        fun fish() {
            while(!clickFishingSpot()) { GlobalWalking.walkTo(FISHING_SPOT_AREA.randomTile) }
        }

    }
}