package cn.nukkit.item;

import cn.nukkit.api.DeprecationDetails;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;

import static cn.nukkit.utils.Utils.dynamic;

public interface ItemID {
    @PowerNukkitOnly
    int STRING_IDENTIFIED_ITEM = dynamic(255);
    int IRON_SHOVEL = 256;
    int IRON_PICKAXE = 257;
    int IRON_AXE = 258;
    int FLINT_STEEL = 259;
    int FLINT_AND_STEEL = 259;
    int APPLE = 260;
    int BOW = 261;
    int ARROW = 262;
    int COAL = 263;
    int DIAMOND = 264;
    int IRON_INGOT = 265;
    int GOLD_INGOT = 266;
    int IRON_SWORD = 267;
    int WOODEN_SWORD = 268;
    int WOODEN_SHOVEL = 269;
    int WOODEN_PICKAXE = 270;
    int WOODEN_AXE = 271;
    int STONE_SWORD = 272;
    int STONE_SHOVEL = 273;
    int STONE_PICKAXE = 274;
    int STONE_AXE = 275;
    int DIAMOND_SWORD = 276;
    int DIAMOND_SHOVEL = 277;
    int DIAMOND_PICKAXE = 278;
    int DIAMOND_AXE = 279;
    int STICK = 280;
    int STICKS = 280;
    int BOWL = 281;
    int MUSHROOM_STEW = 282;
    int GOLD_SWORD = 283;
    int GOLDEN_SWORD = 283;
    int GOLD_SHOVEL = 284;
    int GOLDEN_SHOVEL = 284;
    int GOLD_PICKAXE = 285;
    int GOLDEN_PICKAXE = 285;
    int GOLD_AXE = 286;
    int GOLDEN_AXE = 286;
    int STRING = 287;
    int FEATHER = 288;
    int GUNPOWDER = 289;
    int WOODEN_HOE = 290;
    int STONE_HOE = 291;
    int IRON_HOE = 292;
    int DIAMOND_HOE = 293;
    int GOLD_HOE = 294;
    int GOLDEN_HOE = 294;
    int SEEDS = 295;
    int WHEAT_SEEDS = 295;
    int WHEAT = 296;
    int BREAD = 297;
    int LEATHER_CAP = 298;
    int LEATHER_TUNIC = 299;
    int LEATHER_PANTS = 300;
    int LEATHER_BOOTS = 301;
    int CHAIN_HELMET = 302;
    int CHAIN_CHESTPLATE = 303;
    int CHAIN_LEGGINGS = 304;
    int CHAIN_BOOTS = 305;
    int IRON_HELMET = 306;
    int IRON_CHESTPLATE = 307;
    int IRON_LEGGINGS = 308;
    int IRON_BOOTS = 309;
    int DIAMOND_HELMET = 310;
    int DIAMOND_CHESTPLATE = 311;
    int DIAMOND_LEGGINGS = 312;
    int DIAMOND_BOOTS = 313;
    int GOLD_HELMET = 314;
    int GOLD_CHESTPLATE = 315;
    int GOLD_LEGGINGS = 316;
    int GOLD_BOOTS = 317;
    int FLINT = 318;
    int RAW_PORKCHOP = 319;
    int COOKED_PORKCHOP = 320;
    int PAINTING = 321;
    int GOLDEN_APPLE = 322;
    int SIGN = 323;
    int WOODEN_DOOR = 324;
    int BUCKET = 325;

    int MINECART = 328;
    int SADDLE = 329;
    int IRON_DOOR = 330;
    int REDSTONE = 331;
    int REDSTONE_DUST = REDSTONE;
    int SNOWBALL = 332;
    int BOAT = 333;
    int LEATHER = 334;
    int KELP = 335;
    int BRICK = 336;
    int CLAY = 337;
    int SUGARCANE = 338;
    int SUGAR_CANE = 338;
    int SUGAR_CANES = 338;
    int PAPER = 339;
    int BOOK = 340;
    int SLIMEBALL = 341;
    int CHEST_MINECART = 342;
    @Deprecated
    @DeprecationDetails(since = "1.19.40-r2", reason = "Using new name instead", replaceWith = "CHEST_MINECART")
    int MINECART_WITH_CHEST = CHEST_MINECART;

    int EGG = 344;
    int COMPASS = 345;
    int FISHING_ROD = 346;
    int CLOCK = 347;
    int GLOWSTONE_DUST = 348;
    int RAW_FISH = 349;
    int COOKED_FISH = 350;
    int DYE = 351;
    int BONE = 352;
    int SUGAR = 353;
    int CAKE = 354;
    int BED = 355;
    int REPEATER = 356;
    int COOKIE = 357;
    int MAP = 358;
    int SHEARS = 359;
    int MELON = 360;
    int MELON_SLICE = 360;
    int PUMPKIN_SEEDS = 361;
    int MELON_SEEDS = 362;
    int RAW_BEEF = 363;
    int STEAK = 364;
    int COOKED_BEEF = 364;
    int RAW_CHICKEN = 365;
    int COOKED_CHICKEN = 366;
    int ROTTEN_FLESH = 367;
    int ENDER_PEARL = 368;
    int BLAZE_ROD = 369;
    int GHAST_TEAR = 370;
    int GOLD_NUGGET = 371;
    int GOLDEN_NUGGET = 371;
    int NETHER_WART = 372;
    int POTION = 373;
    int GLASS_BOTTLE = 374;
    int BOTTLE = 374;
    int SPIDER_EYE = 375;
    int FERMENTED_SPIDER_EYE = 376;
    int BLAZE_POWDER = 377;
    int MAGMA_CREAM = 378;
    int BREWING_STAND = 379;
    int BREWING = 379;
    int CAULDRON = 380;
    int ENDER_EYE = 381;
    int GLISTERING_MELON = 382;
    int SPAWN_EGG = 383;
    int EXPERIENCE_BOTTLE = 384;
    int FIRE_CHARGE = 385;
    int BOOK_AND_QUILL = 386;
    int WRITTEN_BOOK = 387;
    int EMERALD = 388;
    int ITEM_FRAME = 389;
    int FLOWER_POT = 390;
    int CARROT = 391;
    int CARROTS = 391;
    int POTATO = 392;
    int POTATOES = 392;
    int BAKED_POTATO = 393;
    int BAKED_POTATOES = 393;
    int POISONOUS_POTATO = 394;
    int EMPTY_MAP = 395;
    int GOLDEN_CARROT = 396;
    int SKULL = 397;
    int CARROT_ON_A_STICK = 398;
    int NETHER_STAR = 399;
    int PUMPKIN_PIE = 400;
    int FIREWORKS = 401;
    int FIREWORKSCHARGE = 402;
    int ENCHANTED_BOOK = 403;
    int ENCHANT_BOOK = 403;
    int COMPARATOR = 404;
    int NETHER_BRICK = 405;
    int QUARTZ = 406;
    int NETHER_QUARTZ = 406;
    int TNT_MINECART = 407;
    @Deprecated
    @DeprecationDetails(since = "1.19.40-r2", reason = "Using new name instead", replaceWith = "TNT_MINECART")
    int MINECART_WITH_TNT = TNT_MINECART;
    int HOPPER_MINECART = 408;
    @Deprecated
    @DeprecationDetails(since = "1.19.40-r2", reason = "Using new name instead", replaceWith = "HOPPER_MINECART")
    int MINECART_WITH_HOPPER = HOPPER_MINECART;
    int PRISMARINE_SHARD = 409;
    int HOPPER = 410;
    int RAW_RABBIT = 411;
    int COOKED_RABBIT = 412;
    int RABBIT_STEW = 413;
    int RABBIT_FOOT = 414;
    int RABBIT_HIDE = 415;
    int LEATHER_HORSE_ARMOR = 416;
    int IRON_HORSE_ARMOR = 417;
    int GOLD_HORSE_ARMOR = 418;
    int DIAMOND_HORSE_ARMOR = 419;
    int LEAD = 420;
    int NAME_TAG = 421;
    int PRISMARINE_CRYSTALS = 422;
    int RAW_MUTTON = 423;
    int COOKED_MUTTON = 424;
    int ARMOR_STAND = 425;
    int END_CRYSTAL = 426;
    int SPRUCE_DOOR = 427;
    int BIRCH_DOOR = 428;
    int JUNGLE_DOOR = 429;
    int ACACIA_DOOR = 430;
    int DARK_OAK_DOOR = 431;
    int CHORUS_FRUIT = 432;
    int POPPED_CHORUS_FRUIT = 433;
    @Since("1.2.1.0-PN")
    int BANNER_PATTERN = 434;

    int DRAGON_BREATH = 437;
    int SPLASH_POTION = 438;

    int LINGERING_POTION = 441;

    int COMMAND_BLOCK_MINECART = 443;
    int ELYTRA = 444;
    int SHULKER_SHELL = 445;
    int BANNER = 446;
    int TOTEM = 450;

    int IRON_NUGGET = 452;

    int TRIDENT = 455;

    int BEETROOT = 457;
    int BEETROOT_SEEDS = 458;
    int BEETROOT_SEED = 458;
    int BEETROOT_SOUP = 459;
    int RAW_SALMON = 460;
    int CLOWNFISH = 461;
    int PUFFERFISH = 462;
    int COOKED_SALMON = 463;
    int DRIED_KELP = 464;
    int NAUTILUS_SHELL = 465;
    int GOLDEN_APPLE_ENCHANTED = 466;
    int HEART_OF_THE_SEA = 467;
    int SCUTE = 468;
    int TURTLE_SHELL = 469;
    int PHANTOM_MEMBRANE = 470;
    int CROSSBOW = 471;
    @PowerNukkitOnly int SPRUCE_SIGN = 472;
    @PowerNukkitOnly int BIRCH_SIGN = 473;
    @PowerNukkitOnly int JUNGLE_SIGN = 474;
    @PowerNukkitOnly int ACACIA_SIGN = 475;
    @PowerNukkitOnly int DARKOAK_SIGN = 476;
    @PowerNukkitOnly int DARK_OAK_SIGN = 476;
    int SWEET_BERRIES = 477;

    int RECORD_13 = 500;
    int RECORD_CAT = 501;
    int RECORD_BLOCKS = 502;
    int RECORD_CHIRP = 503;
    int RECORD_FAR = 504;
    int RECORD_MALL = 505;
    int RECORD_MELLOHI = 506;
    int RECORD_STAL = 507;
    int RECORD_STRAD = 508;
    int RECORD_WARD = 509;
    int RECORD_11 = 510;
    int RECORD_WAIT = 511;

    int SHIELD = 513;

    @Since("1.6.0.0-PNX")
    @PowerNukkitXOnly
    int GLOW_ITEM_FRAME = 623;

    @Since("1.6.0.0-PNX")
    @PowerNukkitXOnly
    int ITEM_MANGROVE_DOOR = 633;

    @Since("1.6.0.0-PNX")
    @PowerNukkitXOnly
    int RECORD_OTHERSIDE = 626;

    @Since("1.6.0.0-PNX")
    @PowerNukkitXOnly
    int MANGROVE_SIGN = 634;
    @Since("1.19.40-r2")
    @PowerNukkitXOnly
    int RECORD_5 = 636;
    @Since("1.19.50-r1")
    @PowerNukkitXOnly
    int DISC_FRAGMENT_5 = 637;
    @Since("1.6.0.0-PNX")
    @PowerNukkitXOnly
    int OAK_CHEST_BOAT = 638;

    @PowerNukkitXOnly
    @Since("1.6.0.0-PNX")
    int BIRCH_CHEST_BOAT = 639;

    @PowerNukkitXOnly
    @Since("1.6.0.0-PNX")
    int JUNGLE_CHEST_BOAT = 640;

    @Since("1.6.0.0-PNX")
    @PowerNukkitXOnly
    int SPRUCE_CHEST_BOAT = 641;

    @PowerNukkitXOnly
    @Since("1.6.0.0-PNX")
    int ACACIA_CHEST_BOAT = 642;

    @PowerNukkitXOnly
    @Since("1.6.0.0-PNX")
    int DARK_OAK_CHEST_BOAT = 643;

    @PowerNukkitXOnly
    @Since("1.6.0.0-PNX")
    int MANGROVE_CHEST_BOAT = 644;

    @PowerNukkitXOnly
    @Since("1.6.0.0-PNX")
    int CHEST_BOAT = 645;
    @PowerNukkitXOnly
    @Since("1.19.50-r1")
    int ECHO_SHARD = 647;

    @Since("1.6.0.0-PNX")
    @PowerNukkitXOnly
    int GLOW_BERRIES = 654;

    @PowerNukkitOnly
    int CAMPFIRE = 720;

    int SUSPICIOUS_STEW = 734;
    int HONEYCOMB = 736;
    int HONEY_BOTTLE = 737;

    @Since("1.4.0.0-PN")
    int LODESTONECOMPASS = 741;
    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    int LODESTONE_COMPASS = LODESTONECOMPASS;
    @Since("1.4.0.0-PN")
    int NETHERITE_INGOT = 742;
    @Since("1.4.0.0-PN")
    int NETHERITE_SWORD = 743;
    @Since("1.4.0.0-PN")
    int NETHERITE_SHOVEL = 744;
    @Since("1.4.0.0-PN")
    int NETHERITE_PICKAXE = 745;
    @Since("1.4.0.0-PN")
    int NETHERITE_AXE = 746;
    @Since("1.4.0.0-PN")
    int NETHERITE_HOE = 747;
    @Since("1.4.0.0-PN")
    int NETHERITE_HELMET = 748;
    @Since("1.4.0.0-PN")
    int NETHERITE_CHESTPLATE = 749;
    @Since("1.4.0.0-PN")
    int NETHERITE_LEGGINGS = 750;
    @Since("1.4.0.0-PN")
    int NETHERITE_BOOTS = 751;
    @Since("1.4.0.0-PN")
    int NETHERITE_SCRAP = 752;
    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    int CRIMSON_SIGN = 753;
    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    int WARPED_SIGN = 754;
    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    int CRIMSON_DOOR = 755;
    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    int WARPED_DOOR = 756;
    @Since("1.4.0.0-PN")
    int WARPED_FUNGUS_ON_A_STICK = 757;
    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    int CHAIN = 758;
    @Since("1.4.0.0-PN")
    int RECORD_PIGSTEP = 759;
    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    int NETHER_SPROUTS = 760;

    @Since("1.6.0.0-PNX")
    int AMETHYST_SHARD = 771;
    @Since("FUTURE")
    int SPYGLASS = 772;

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    int SOUL_CAMPFIRE = 801;
    //@PowerNukkitOnly int DEBUG_STICK = <Possible:> 802+;
}
