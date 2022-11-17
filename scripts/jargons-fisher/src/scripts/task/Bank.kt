package scripts.task

import org.tribot.script.sdk.Bank
import org.tribot.script.sdk.Inventory
import org.tribot.script.sdk.Log
import org.tribot.script.sdk.query.Query
import org.tribot.script.sdk.walking.GlobalWalking
import scripts.data.Var.Companion.cookedFishList
import scripts.data.Var.Companion.fishingEquipment
import scripts.data.Var.Companion.rawFishList
import scripts.data.Var.Companion.rawFishListID
import scripts.data.Var.Companion.shouldCook

class Bank {

    companion object {

        fun validate(): Boolean {
            return(Inventory.isFull() && !Inventory.contains(rawFishListID[0]) && !Inventory.contains(rawFishListID[1])
                    || Inventory.isFull() && !shouldCook || !Query.inventory().nameContains(fishingEquipment).isAny)
        }

        fun bank(): Boolean {
            GlobalWalking.walkToBank()
            Bank.ensureOpen()
            if(!Query.inventory().nameContains(fishingEquipment).isAny) {
                if(!Bank.contains("Small fishing net")) {
                    Log.error("Could not find a small fishing net in the inventory or bank! Ending script.")
                    return false
                }
                Bank.depositInventory()
                Bank.withdraw(fishingEquipment,1)
            } else {
                for(item in cookedFishList) { Bank.depositAll(item) }
                for(item in rawFishList) { Bank.depositAll(item) }
            }
            return true
        }

    }
}