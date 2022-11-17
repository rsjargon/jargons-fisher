package scripts.data

import org.tribot.script.sdk.Log
import org.tribot.script.sdk.painting.Painting
import org.tribot.script.sdk.painting.template.basic.BasicPaintTemplate
import org.tribot.script.sdk.painting.template.basic.PaintLocation
import org.tribot.script.sdk.painting.template.basic.PaintRows
import org.tribot.script.sdk.painting.template.basic.PaintTextRow
import scripts.data.Var.Companion.shouldCook
import java.awt.Graphics2D

class GUI {

    companion object {

        fun initGUI() {
            val template = PaintTextRow.builder().background(java.awt.Color.blue.brighter()).build()
            val paint = BasicPaintTemplate.builder()
                .row(PaintRows.scriptName(template.toBuilder()))
                .row(PaintRows.runtime(template.toBuilder()))
                .row(template.toBuilder().label("Toggle Cooking").onClick { toggleCooking() }.build())
                .location(PaintLocation.BOTTOM_LEFT_VIEWPORT)
                .build()
            Painting.addPaint { g: Graphics2D? -> paint.render(g!!) }
        }

        private fun toggleCooking() {
            shouldCook = !shouldCook
            Log.info("Cooking is now $shouldCook")
        }

    }

}