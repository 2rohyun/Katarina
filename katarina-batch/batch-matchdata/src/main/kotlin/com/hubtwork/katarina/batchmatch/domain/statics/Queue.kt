package com.hubtwork.katarina.batchmatch.domain.statics


enum class Queue(val queueId: Int, val tag: String) {

    /**
     *          Summoner's Rift
     *
     */
    ALL_RANDOM(325, "Summoner's Rift_AR_5X5"),
    ALL_RANDOM_URF(900, "Summoner's Rift_ARURF_5X5"),
    ALL_RANDOM_URF_SNOW(1010, "Summoner's Rift_ARURFS_5X5"),
    BLACK_MARKET_BRAWLERS(313, "Summoner's Rift_Brawlers_5X5"),
    BLIND_PICK(430, "Summoner's Rift_NORMAL_5V5_BLIND_PICK"),
    BLOOD_HUNT_ASSASSIN(600, "Summoner's Rift_ASSASSINATE_5X5"),
    CLASH(700, "Summoner's Rift_Clash"),
    COOP_VS_AI_BEGINNER(840, "Summoner's Rift_BOT_Beginner"),
    COOP_VS_AI_INTERMEDIATE(850, "Summoner's Rift_BOT_Intermediate"),
    COOP_VS_AI_INTRO(830, "Summoner's Rift_BOT_INTRO"),
    COOP_VS_AI_URF(83, "Summoner's Rift_BOT_URF_5X5"),
    DOOM_BOTS(960, "Summoner's Rift_BOT_Doom_5X5"),
    DOOM_BOTS_WITH_VOTING(950, "Summoner's Rift_BOT_Doom_5X5_VOTE"),
    HEXAKILL(75, "Summoner's Rift_HEXAKILL_6X6"),
    NEMESIS_DRAFT(310, "Summoner's Rift_NEMESIS"),
    NEXUS_SIEGE(940, "Summoner's Rift_NEXUS"),
    NORMAL(400, "Summoner's Rift_DRAFT_UNRANKED_5X5"),
    ONE_FOR_ALL(1020, "Summoner's Rift_ONEFORALL_RAPID_5X5"),
    RANKED_FLEX(440, "Summoner's Rift_RANKED_FLEX_SR"),
    RANKED_SOLO(420, "Summoner's Rift_RANKED_SOLO_5x5"),
    URF(76, "Summoner's Rift_URF_5X5"),

    /**
     *          Howling Abyss ( 칼바람 나락 )
     *
     */
    ARAM(450, "Howling Abyss_ARAM"),
    ONE_FOR_ALL_MIRROR(78, "Howling Abyss_ONEFORALL_MIRRORMODE_5X5"),
    KINGPORO(920, "Howling Abyss_KINGPORO"),
    SHOWDOWN_DUO(73, "Howling Abyss_FIRSTBLOOD_2X2"),
    SHOWDOWN_SOLO(72, "Howling Abyss_FIRSTBLOOD_1X1"),

    /**
     *          Twisted Treeline
     *
     */
    COOP_VS_AI_THREES_BEGINNER(820, "Twisted Treeline_BOT_Beginner"),
    COOP_VS_AI_THREES_INTERMEDIATE(800, "Twisted Treeline_BOT_Intermediate"),
    COOP_VS_AI_THREES_INTRO(810, "Twisted Treeline_BOT_Intro"),
    HEXAKILL_THREES(98, "Twisted Treeline_HEXAKILL"),
    RANKED_THREES(470, "Twisted Treeline_RANKED_FLEX_TT"),
    THREES(460, "Twisted Treeline_NORMAL_3X3_BLIND_PICK"),

    /**
     *          TFT ( 전략적 팀 전투 )
     *
     */
    RANKED_TFT(1100, "TFT_RANKED"),
    TFT(1090, "TFT_NORMAL"),

    /**
     *          Butcher's Bridge
     *
     */
    BUTCHERS_BRIDGE(100, "ARAM_BUTCHERS_5X5"),

    /**
     *          Others
     *
     */
    CUSTOM(0, "Custom Game"),

    /**
     * Crystal Scar
     * <p>
     * Ascension games
     */
    ASCENSION(910, "PROJECT"),

    /**
     * Cosmic Ruins
     * <p>
     * Dark Star: Singularity games
     */
    DARKSTAR(610, "DARKSTAR_3X3"),

    /**
     * Crystal Scar
     * <p>
     * Definitely Not Dominion games
     */
    DEFINITELY_NOT_DOMINION(317, "DEFINITELY_NOT_DOMINION_5X5"),

    /**
     * Crash Site
     * <p>
     * Odyssey Extraction: Cadet games
     */
    ODYSSEY_CADET(1040, "Odyssey_Cadet"),

    /**
     * Crash Site
     * <p>
     * Odyssey Extraction: Captain games
     */
    ODYSSEY_CAPTAIN(1060, "Odyssey_Captain"),

    /**
     * Crash Site
     * <p>
     * Odyssey Extraction: Crewmember games
     */
    ODYSSEY_CREWMEMBER(1050, "Odyssey_Crew member"),

    /**
     * Crash Site
     * <p>
     * Odyssey Extraction: Intro games
     */
    ODYSSEY_INTRO(1030, "Odyssey_Intro"),

    /**
     * Crash Site
     * <p>
     * Odyssey Extraction: Onslaught games
     */
    ODYSSEY_ONSLAUGHT(1070, "Odyssey_Onslaught"),

    /**
     * Overcharge
     * <p>
     * PROJECT: Hunters games
     */
    OVERCHARGE(1000, "Overcharge_Hunters");

    companion object {

        fun valueOf(queueId: Int): Queue? = Queue.values().find { it.queueId == queueId }

        fun getTag(id: Int): String {
            for (queue in Queue.values()) {
                if (queue.queueId == id) {
                    return queue.tag
                }
            }
            return "[ Err ] No Such queue mapped info"
        }


    }

}