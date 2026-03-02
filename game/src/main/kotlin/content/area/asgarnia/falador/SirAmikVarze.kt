package content.area.asgarnia.falador

import content.entity.player.dialogue.*
import content.entity.player.dialogue.type.*
import content.quest.quest
import content.quest.refreshQuestJournal
import world.gregs.voidps.engine.Script
import world.gregs.voidps.engine.entity.character.player.Player
import world.gregs.voidps.engine.inv.add
import world.gregs.voidps.engine.inv.inventory

class SirAmikVarze : Script {

    init {
        npcOperate("Talk-to", "sir_amik_varze_falador") {
            when (quest("black_knights_fortress")) {
                "unstarted" -> {
                    npc<Quiz>(
                        "I am the leader of the White Knights of Falador.\n" +
                            "Why do you seek my audience?",
                    )

                    choice {
                        option("I seek a quest!") {
                            blackKnightsFortressSeekingAQuest()
                        }
                        option("I don't, I'm just looking around.") {
                            player<No>("I don't, I'm just looking around.")
                            npc<Neutral>("Ok. Please don't break anything.")
                        }
                    }
                }
                // TODO: mid quest dialogue
            }
        }
    }

    suspend fun Player.blackKnightsFortressSeekingAQuest() {
        // TODO: How should it be handled if the player doesn't have the requirements?

        player<Happy>("I seek a quest!")
        npc<Neutral>(
            "Well, I need some spy work doing but it's quite dangerous.\n" +
                "It will involve going into the Black Knights' fortress.",
        )
        choice {
            option("I laugh in the face of danger!") {
                player<Laugh>("I laugh in the face of danger!")
                npc<Neutral>("Well that's good. Don't get too overconfident though.")

                startBlackKnightsFortress()
            }
            option("I go and cower in a corner at the first sign of danger!") {
                player<Scared>("I go and cower in a corner at the first sign of danger!")
                npc<Confused>("Err....")
                npc<Confused>("Well.")
                npc<Confused>("I... suppose spy work DOES involve a little hiding in corners.")
                choice {
                    option("Oh. I suppose I'll give it a go then.") {
                        player<Neutral>("Oh. I suppose I'll give it a go then.")

                        startBlackKnightsFortress()
                    }
                    option("No, I'm not ready to do that.") {
                        player<No>("No, I'm not ready to do that.")
                    }
                }
            }
        }
    }

    suspend fun Player.startBlackKnightsFortress() {npc<Neutral>(
        "You've come along at just the right time actually.\n" +
                "All of my knights are already known to the Black Knights.",
    )
        npc<Neutral>("Subtlety isn't exactly our strong point.")
        player<Neutral>(
            "Can't you just take your White Knights' armour off?\n" +
                    "They wouldn't recognise you then!",
        )
        npc<No>(
            "I am afraid our charter prevents us using espionage in any form,\n" +
                    "that is the domain of the Temple Knights.",
        )
        player<Quiz>("Temple Knights? Who are they?")
        npc<Neutral>(
            "That information is classified.\n" +
                    "I am forbidden to share it with outsiders.",
        )
        player<Quiz>("So... what do you need doing?")
        npc<Neutral>(
            "Well, the Black Knights have started making strange threats to us;\n" +
                    "demanding large amounts of money and land, and threatening to invade\n" +
                    "Falador if we don't pay them.",
        )
        npc<Admit>("Now, NORMALLY this wouldn't be a problem...")
        npc<Admit>("But they claim to have a powerful new secret weapon.")
        npc<Neutral>(
            "Your mission, should you decide to accept it, is to infiltrate their fortress,\n" +
                    "find out what their secret weapon is, and then sabotage it.",
        )
        choice {
            option("Yes.") {
                player<Yes>("Ok, I'll do my best")
                npc<Happy>(
                    "Good luck! Let me know how you get on. Here's the dossier for the case,\n" +
                            "I've already given you the details.",
                )

                if (!inventory.add("dossier")) {
                    npc<No>(
                        "Oh. You don't appear to have any room for the dossier in your inventory.\n" +
                                "Come back when you do."
                    )

                    return@option
                }

                set("black_knights_fortress", "started") // TODO: make these constants
                refreshQuestJournal()
            }
            option("No.") {
                player<No>("No, I'm not ready to do that.")
                npc<Neutral>("Come see me again if you change your mind.")
            }
        }
    }
}
