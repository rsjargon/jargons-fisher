package scripts

import org.tribot.script.sdk.Camera
import org.tribot.script.sdk.Camera.RotationMethod
import org.tribot.script.sdk.Log
import org.tribot.script.sdk.Waiting
import org.tribot.script.sdk.script.TribotScript
import org.tribot.script.sdk.script.TribotScriptManifest
import org.tribot.script.sdk.walking.GlobalWalking
import scripts.data.Area.Companion.AL_KHARID_BANK_TILE
import scripts.data.GUI.Companion.initGUI
import scripts.data.Var.Companion.shouldCook
import scripts.task.Bank
import scripts.task.Cook
import scripts.task.Fish
import java.util.function.BooleanSupplier

@TribotScriptManifest(name = "Jargons Al Kharid Fisher", author = "Jargon", category = "Fishing", description = "BFishes shrimp/anchovies in Al Kharid, then proceeds to cook and/or bank them")
class MyScript : TribotScript {
    override fun execute(args: String) {

        var scriptActive = true
        initGUI()
        Log.info("Jargon's Fisher Version 1")
        Camera.setRotationMethod(RotationMethod.MOUSE)

        // Walks to Al Kharid Bank if not currently nearby
        if (AL_KHARID_BANK_TILE.distance() > 30) {
            if (GlobalWalking.walkTo(AL_KHARID_BANK_TILE) && Waiting.waitUntil(BooleanSupplier {
                    AL_KHARID_BANK_TILE.distance() <= 5 })) {
                Waiting.waitNormal(600, 90)
            }
        }

        while(scriptActive) {
            if(Bank.validate()) {
                if(!Bank.bank()) { scriptActive = false }
            }
            if(Fish.validate()) { Fish.fish() }
            if(Cook.validate() && shouldCook) { Cook.cook() }
            Waiting.waitNormal(60, 20)
        }

    }
}