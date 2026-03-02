package content.quest.free.black_knights_fortress

import content.quest.quest
import content.quest.questJournal
import world.gregs.voidps.engine.Script

class BlackKnightsFortress : Script {

    init {
        questJournalOpen("black_knights_fortress") {
            val lines = when (quest("cooks_assistant")) {
                "started" -> {
                    var list = mutableListOf(
                        "<navy>I have spoken to <maroon>Sir Amik Varze<navy>.",
                        "<navy>He told me that the <maroon>Black Knights <navy>are up to no good,",
                        "<navy>and have some kind of secret weapon.",
                    )

                    list
                }
                "completed" -> listOf(
                    // TODO: line breaks
                    // TODO: this is the entire log, i'm guessing that's not what is actually needed
                    "<str>I have spoken to Sir Amik Varze. He told me that the Black Knights are up to no good, and have some kind of secret weapon.",
                    "<str>I have been to the Black Knights' Fortress and successfully managed to spy on a meeting between a Witch, a Black Knight, and a Goblin.",
                    "<str>I heard that they are making an invincibility potion, but that it would be ruined if a cabbage (not from Draynor Manor) was added to the cauldron.",
                    "<str>I successfully sabotaged the potion by dropping a cabbage into the cauldron from the floor above.",
                    "<str>I have reported my success to <maroon>Sir Amik Varze<str>.",
                    "",
                    "<red>QUEST COMPLETE!",
                )
                else -> {
                    var list = mutableListOf(
                        "<navy>I can start this quest by speaking to <maroon>Sir Amik Varze <navy>at the",
                        "<maroon>White Knight's Castle <navy>in <maroon>Falador<navy>.",
                    )

                    if (get("quest_points", 0) > 12) {
                        list.add("<str>I have a total of at least 12 Quest Points")
                    } else {
                        list.add("navy>I need at least 12 Quest Points")
                    }

                    list.add("<navy>I would have an advantage if I could fight <maroon>Level 33 Knights")
                    list.add("<navy>and aif i had a smithing level of <maroon>26<navy>.")

                    list
                }
            }
            questJournal("Black Knights Fortress", lines)
        }
    }
}
