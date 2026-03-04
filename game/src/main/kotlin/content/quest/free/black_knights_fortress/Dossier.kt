package content.quest.free.black_knights_fortress

import world.gregs.voidps.engine.Script
import world.gregs.voidps.engine.client.message
import world.gregs.voidps.engine.inv.inventory
import world.gregs.voidps.engine.inv.remove

class Dossier: Script {

    init {
        itemOption("Read", "dossier") {
            message("Infiltrate fortress...")
            message("sabotage secret weapon...")
            message("self destruct in 3...2...1...")

            inventory.remove("dossier")

            // TODO: should the quest status change to prevent the player getting multiple dossier?
        }
    }
}