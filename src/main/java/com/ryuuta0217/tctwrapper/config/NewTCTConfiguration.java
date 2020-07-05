package com.ryuuta0217.tctwrapper.config;

import org.bitbucket.jack_basukeraihu.TroubleInCrafterTown.tct.Config;
import org.bitbucket.jack_basukeraihu.TroubleInCrafterTown.tct.items.ItemType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * TCTConfigurationとの差異は
 * @see com.ryuuta0217.tctwrapper.config.NewTCTConfiguration.RoleSetting.Fox.Shop
 * のあたりだけ
 *
 * 使う予定があるかと言われたらない
 */
public class NewTCTConfiguration {
    private static final File confFile = new File("plugins/Trouble_in_Crafter_Town/config.yml");
    private static final YamlConfiguration conf = YamlConfiguration.loadConfiguration(confFile);

    public static void setLanguage(LanguageType newLanguage) {
        conf.set("Language", newLanguage.getFileName());
    }

    public static void setConsoleCommandToWinner(List<String> commands) {
        conf.set("ConsoleCommandToWinner", commands);
    }

    public static void addConsoleCommandToWinner(String... commands) {
        String path = "ConsoleCommandToWinner";
        List<String> currentCommands = conf.getStringList(path);
        Collections.addAll(currentCommands, commands);
        conf.set(path, currentCommands);
    }

    public static void setConsoleCommandToLoser(List<String> commands) {
        conf.set("ConsoleCommandToLoser", commands);
    }

    public static void addConsoleCommandToLoser(String... commands) {
        String path = "ConsoleCommandToLoser";
        List<String> currentCommands = conf.getStringList(path);
        Collections.addAll(currentCommands, commands);
        conf.set(path, currentCommands);
    }

    public static class GameSetting {
        private static final ConfigurationSection gameSetting = conf.getConfigurationSection("GameSetting");

        public static int getDefaultHealth() {
            return Config.getDefaultHealth();
        }

        public static void setDefaultHealth(int newHealth) {
            gameSetting.set("DefaultHealth", newHealth);
        }

        public static int getPrepartionTime() {
            return Config.getPreparationTime();
        }

        public static void setPreparationTime(int newPrepareTime) {
            gameSetting.set("PreparationTime", newPrepareTime);
        }

        public static int getGiveCoinTime() {
            return Config.getGiveCoinTime();
        }

        public static void setGiveCoinTime(int newGiveCoinTime) {
            gameSetting.set("GiveCoinTime", newGiveCoinTime);
        }

        public static int getExtendTime() {
            return Config.getExtendTime();
        }

        public static void setExtendTime(int newExtendTime) {
            gameSetting.set("ExtendTime", newExtendTime);
        }

        public static int getDefaultEndTime() {
            return Config.getDefaultEndTime();
        }

        public static void setDefaultEndTime(int newDefaultEndTime) {
            gameSetting.set("DefaultEndTime", newDefaultEndTime);
        }
    }

    public static class GameModeSetting {
        private static final ConfigurationSection gameModeSetting = conf.getConfigurationSection("GameModeSetting");

        public static boolean isJapaneseChatMode() {
            return Config.isJapaneseChatMode();
        }

        public static void setJapaneseChatMode(boolean enable) {
            gameModeSetting.set("JapaneseChatMode", enable);
        }

        public static boolean isInfiniteTimeMode() {
            return Config.isInfiniteTimeMode();
        }

        public static void setInfiniteTimeMode(boolean enable) {
            gameModeSetting.set("InfiniteTimeMode", enable);
        }

        public static boolean isSelfDeclaredMode() {
            return Config.isSelfDeclaredMode();
        }

        public static void setSelfDeclaredMode(boolean enable) {
            gameModeSetting.set("SelfDeclaredMode", enable);
        }
    }

    public static class CustomModeSetting {
        private static final ConfigurationSection customModeSetting = conf.getConfigurationSection("CustomModeSetting");

        public static boolean isCustomModeEnabled() {
            return Config.isCustomMode();
        }

        public static void setCustomModeEnabled(boolean enable) {
            customModeSetting.set("Enable", enable);
        }

        public static int getWolfCount() {
            return Config.getCustom_Wolf();
        }

        public static void setWolfCount(int newWolfCount) {
            customModeSetting.set("Wolf", newWolfCount);
        }

        public static int getDetectiveCount() {
            return Config.getCustom_Detective();
        }

        public static void setDetectiveCount(int newDetectiveCount) {
            customModeSetting.set("Detective", newDetectiveCount);
        }

        public static int getDoctorCount() {
            return Config.getCustom_Doctor();
        }

        public static void setDoctorCount(int newDoctorCount) {
            customModeSetting.set("Doctor", newDoctorCount);
        }

        public static int getMadmanCount() {
            return Config.getCustom_Madman();
        }

        public static void setMadmanCount(int newMadmanCount) {
            customModeSetting.set("Madman", newMadmanCount);
        }

        public static int getFoxCount() {
            return Config.getCustom_Fox();
        }

        public static void setFoxCount(int newFoxCount) {
            customModeSetting.set("Fox", newFoxCount);
        }
    }

    public static class RoleSetting {
        private static final ConfigurationSection roleSetting = conf.getConfigurationSection("RoleSetting");

        public static class Villager {
            private static final ConfigurationSection villager = roleSetting.getConfigurationSection("Villager");

            public static int getDefaultCoin() {
                return Config.getVillager_DefaultCoin();
            }

            public static void setDefaultCoin(int newCoinCount) {
                villager.set("DefaultCoin", newCoinCount);
            }

            public static class Shop {
                private static final ConfigurationSection shop = villager.getConfigurationSection("Shop");

                public static boolean getStoneSwordEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.STONE_SWORD);
                }

                public static void setStoneSwordEnabled(boolean enabled) {
                    shop.set("StoneSword", enabled);
                }

                public static boolean getBowEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.BOW);
                }

                public static void setBowEnabled(boolean enabled) {
                    shop.set("Bow", enabled);
                }

                public static boolean getSpeedPotionEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.SPEED_POTION);
                }

                public static void setSpeedPotionEnabled(boolean enabled) {
                    shop.set("SpeedPotion", enabled);
                }

                public static boolean getAnalyzerEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.ANALYZER_HOE);
                }

                public static void setAnalyzerEnabled(boolean enabled) {
                    shop.set("Analyzer", enabled);
                }

                public static boolean getCurseEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.CURSE_BIN);
                }

                public static void setCurseEnabled(boolean enabled) {
                    shop.set("Curse", enabled);
                }

                public static boolean getMilkEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.MILK);
                }

                public static void setMilkEnabled(boolean enabled) {
                    shop.set("Milk", enabled);
                }

                public static boolean getHealPotionEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.HEAL_POTION);
                }

                public static void setHealPotionEnabled(boolean enabled) {
                    shop.set("HealPotion", enabled);
                }

                public static boolean getCrackerEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.CRACKER);
                }

                public static void setCrackerEnabled(boolean enabled) {
                    shop.set("Cracker", enabled);
                }
            }
        }

        public static class Detective {
            private static final ConfigurationSection detective = roleSetting.getConfigurationSection("Detective");

            public static int getDefaultCoin() {
                return Config.getDetective_DefaultCoin();
            }

            public static void setDefaultCoin(int newCoinCount) {
                detective.set("DefaultCoin", newCoinCount);
            }

            public static class Shop {
                private static final ConfigurationSection shop = detective.getConfigurationSection("Shop");

                public static boolean getIronSwordEnabled() {
                    return !Config.getDisableItem_Detective().contains(ItemType.IRON_SWORD);
                }

                public static void setIronSwordEnabled(boolean enabled) {
                    shop.set("IronSword", enabled);
                }

                public static boolean getDetectiveSwordEnabled() {
                    return !Config.getDisableItem_Detective().contains(ItemType.DETECTIVE_SWORD);
                }

                public static void setDetectiveSwordEnabled(boolean enabled) {
                    shop.set("DetectiveSword", enabled);
                }

                public static boolean getHelmetEnabled() {
                    return !Config.getDisableItem_Detective().contains(ItemType.ARMOR);
                }

                public static void setHelmetEnabled(boolean enabled) {
                    shop.set("Helmet", enabled);
                }

                public static boolean getHealStationEnabled() {
                    return !Config.getDisableItem_Detective().contains(ItemType.HEALTH_STATION);
                }

                public static void setHealStationEnabled(boolean enabled) {
                    shop.set("HealStation", enabled);
                }

                public static boolean getBowEnabled() {
                    return !Config.getDisableItem_Detective().contains(ItemType.BOW);
                }

                public static void setBowEnabled(boolean enabled) {
                    shop.set("Bow", enabled);
                }

                public static boolean getSpeedPotionEnabled() {
                    return !Config.getDisableItem_Detective().contains(ItemType.SPEED_POTION);
                }

                public static void setSpeedPotionEnabled(boolean enabled) {
                    shop.set("SpeedPotion", enabled);
                }

                public static boolean getSeedsEnabled() {
                    return !Config.getDisableItem_Detective().contains(ItemType.SEEDS);
                }

                public static void setSeedsEnabled(boolean enabled) {
                    shop.set("Seeds", enabled);
                }

                public static boolean getAntiExplosionEnabled() {
                    return !Config.getDisableItem_Detective().contains(ItemType.ANTI_EXPLOSION);
                }

                public static void setAntiExplosionEnabled(boolean enabled) {
                    shop.set("AntiExplosion", enabled);
                }

                public static boolean getHealPotionEnabled() {
                    return !Config.getDisableItem_Detective().contains(ItemType.HEAL_POTION);
                }

                public static void setHealPotionEnabled(boolean enabled) {
                    shop.set("HealPotion", enabled);
                }

                public static boolean getCrackerEnabled() {
                    return !Config.getDisableItem_Detective().contains(ItemType.CRACKER);
                }

                public static void setCrackerEnabled(boolean enabled) {
                    shop.set("Cracker", enabled);
                }
            }
        }

        public static class Wolf {
            private static final ConfigurationSection wolf = roleSetting.getConfigurationSection("Wolf");

            public static int getDefaultCoin() {
                return Config.getWolf_DefaultCoin();
            }

            public static void setDefaultCoin(int newCoinCount) {
                wolf.set("DefaultCoin", newCoinCount);
            }

            public static class Shop {
                private static final ConfigurationSection shop = wolf.getConfigurationSection("Shop");

                public static boolean getStoneSwordEnabled() {
                    return !Config.getDisableItem_Wolf().contains(ItemType.STONE_SWORD);
                }

                public static void setStoneSwordEnabled(boolean enabled) {
                    shop.set("StoneSword", enabled);
                }

                public static boolean getTntEnabled() {
                    return !Config.getDisableItem_Wolf().contains(ItemType.TNT);
                }

                public static void setTntEnabled(boolean enabled) {
                    shop.set("TNT", enabled);
                }

                public static boolean getCompassEnabled() {
                    return !Config.getDisableItem_Wolf().contains(ItemType.COMPASS);
                }

                public static void setCompassEnabled(boolean enabled) {
                    shop.set("Compass", enabled);
                }

                public static boolean getBowEnabled() {
                    return !Config.getDisableItem_Wolf().contains(ItemType.BOW);
                }

                public static void setBowEnabled(boolean enabled) {
                    shop.set("Bow", enabled);
                }

                public static boolean getSnowBallEnabled() {
                    return !Config.getDisableItem_Wolf().contains(ItemType.SNOWBALL);
                }

                public static void setSnowBallEnabled(boolean enabled) {
                    shop.set("SnowBall", enabled);
                }

                public static boolean getFireEnabled() {
                    return !Config.getDisableItem_Wolf().contains(ItemType.FIRE);
                }

                public static void setFireEnabled(boolean enabled) {
                    shop.set("Fire", enabled);
                }

                public static boolean getWolfSwordEnabled() {
                    return !Config.getDisableItem_Wolf().contains(ItemType.INSTANTSWORD);
                }

                public static void setWolfSwordEnabled(boolean enabled) {
                    shop.set("WolfSword", enabled);
                }

                public static boolean getSpeedPotionEnabled() {
                    return !Config.getDisableItem_Wolf().contains(ItemType.SPEED_POTION);
                }

                public static void setSpeedPotionEnabled(boolean enabled) {
                    shop.set("SpeedPotion", enabled);
                }

                public static boolean getTeleportDiamondEnabled() {
                    return !Config.getDisableItem_Wolf().contains(ItemType.TELEPORT_DIAMOND);
                }

                public static void setTeleportDiamondEnabled(boolean enabled) {
                    shop.set("TeleportDiamond", enabled);
                }

                public static boolean getAnalyzerEnabled() {
                    return !Config.getDisableItem_Wolf().contains(ItemType.ANALYZER_HOE);
                }

                public static void setAnalyzerEnabled(boolean enabled) {
                    shop.set("Analyzer", enabled);
                }

                public static boolean getCurseEnabled() {
                    return !Config.getDisableItem_Wolf().contains(ItemType.CURSE_BIN);
                }

                public static void setCurseEnabled(boolean enabled) {
                    shop.set("Curse", enabled);
                }

                public static boolean getCreeperEnabled() {
                    return !Config.getDisableItem_Wolf().contains(ItemType.CREEPER);
                }

                public static void setCreeperEnabled(boolean enabled) {
                    shop.set("Creeper", enabled);
                }

                public static boolean getDummyEnabled() {
                    return !Config.getDisableItem_Wolf().contains(ItemType.DUMMY);
                }

                public static void setDummyEnabled(boolean enabled) {
                    shop.set("Dummy", enabled);
                }

                public static boolean getHealPotionEnabled() {
                    return !Config.getDisableItem_Wolf().contains(ItemType.HEAL_POTION);
                }

                public static void setHealPotionEnabled(boolean enabled) {
                    shop.set("HealPotion", enabled);
                }

                public static boolean getCrackerEnabled() {
                    return !Config.getDisableItem_Wolf().contains(ItemType.CRACKER);
                }

                public static void setCrackerEnabled(boolean enabled) {
                    shop.set("Cracker", enabled);
                }

            }
        }

        public static class Doctor {
            private static final ConfigurationSection doctor = roleSetting.getConfigurationSection("Doctor");

            public static int getDefaultCoin() {
                return Config.getDoctor_DefaultCoin();
            }

            public static void setDefaultCoin(int newCoinCount) {
                doctor.set("DefaultCoin", newCoinCount);
            }

            public static class Shop {
                private static final ConfigurationSection shop = doctor.getConfigurationSection("Shop");

                public static boolean getStoneSwordEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.STONE_SWORD);
                }

                public static void setStoneSwordEnabled(boolean enabled) {
                    shop.set("StoneSword", enabled);
                }

                public static boolean getBowEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.BOW);
                }

                public static void setBowEnabled(boolean enabled) {
                    shop.set("Bow", enabled);
                }

                public static boolean getSpeedPotionEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.SPEED_POTION);
                }

                public static void setSpeedPotionEnabled(boolean enabled) {
                    shop.set("SpeedPotion", enabled);
                }

                public static boolean getAnalyzerEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.ANALYZER_HOE);
                }

                public static void setAnalyzerEnabled(boolean enabled) {
                    shop.set("Analyzer", enabled);
                }

                public static boolean getCurseEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.CURSE_BIN);
                }

                public static void setCurseEnabled(boolean enabled) {
                    shop.set("Curse", enabled);
                }

                public static boolean getMilkEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.MILK);
                }

                public static void setMilkEnabled(boolean enabled) {
                    shop.set("Milk", enabled);
                }

                public static boolean getHealPotionEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.HEAL_POTION);
                }

                public static void setHealPotionEnabled(boolean enabled) {
                    shop.set("HealPotion", enabled);
                }

                public static boolean getCrackerEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.CRACKER);
                }

                public static void setCrackerEnabled(boolean enabled) {
                    shop.set("Cracker", enabled);
                }
            }
        }

        public static class Madman {
            private static final ConfigurationSection madman = roleSetting.getConfigurationSection("Madman");

            public static int getDefaultCoin() {
                return Config.getMadman_DefaultCoin();
            }

            public static void setDefaultCoin(int newCoinCount) {
                madman.set("DefaultCoin", newCoinCount);
            }

            public static class Shop {
                private static final ConfigurationSection shop = roleSetting.getConfigurationSection("Shop");

                public static boolean getStoneSwordEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.STONE_SWORD);
                }

                public static void setStoneSwordEnabled(boolean enabled) {
                    shop.set("StoneSword", enabled);
                }

                public static boolean getBowEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.BOW);
                }

                public static void setBowEnabled(boolean enabled) {
                    shop.set("Bow", enabled);
                }

                public static boolean getSpeedPotionEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.SPEED_POTION);
                }

                public static void setSpeedPotionEnabled(boolean enabled) {
                    shop.set("SpeedPotion", enabled);
                }

                public static boolean getAnalyzerEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.ANALYZER_HOE);
                }

                public static void setAnalyzerEnabled(boolean enabled) {
                    shop.set("Analyzer", enabled);
                }

                public static boolean getCurseEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.CURSE_BIN);
                }

                public static void setCurseEnabled(boolean enabled) {
                    shop.set("Curse", enabled);
                }

                public static boolean getMilkEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.MILK);
                }

                public static void setMilkEnabled(boolean enabled) {
                    shop.set("Milk", enabled);
                }

                public static boolean getHealPotionEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.HEAL_POTION);
                }

                public static void setHealPotionEnabled(boolean enabled) {
                    shop.set("HealPotion", enabled);
                }

                public static boolean getCrackerEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.CRACKER);
                }

                public static void setCrackerEnabled(boolean enabled) {
                    shop.set("Cracker", enabled);
                }
            }
        }

        public static class Fox {
            private static final ConfigurationSection fox = roleSetting.getConfigurationSection("Fox");

            public static int getDefaultCoin() {
                return Config.getFox_DefaultCoin();
            }

            public static void setDefaultCoin(int newCoinCount) {
                fox.set("DefaultCoin", newCoinCount);
            }

            public static class Shop {
                private static final ConfigurationSection shop = fox.getConfigurationSection("Shop");
                public static ShopConfiguration<Boolean> STONE_SWORD = new ShopConfiguration<>(shop, "StoneSword", true);

                public ShopConfiguration<?> valueOf(String name) {
                    if(name.equalsIgnoreCase("stone_sword")) {
                        return STONE_SWORD;
                    } else {
                        throw new IllegalArgumentException(name + " は存在しません");
                    }
                }

                public static String[] values() {
                    return new String[]{"STONE_SWORD"};
                }
            }

            /*public static class Shop {
                private static final ConfigurationSection shop = fox.getConfigurationSection("Shop");

                public static boolean getStoneSwordEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.STONE_SWORD);
                }

                public static void setStoneSwordEnabled(boolean enabled) {
                    shop.set("StoneSword", enabled);
                }

                public static boolean getBowEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.BOW);
                }

                public static void setBowEnabled(boolean enabled) {
                    shop.set("Bow", enabled);
                }

                public static boolean getSpeedPotionEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.SPEED_POTION);
                }

                public static void setSpeedPotionEnabled(boolean enabled) {
                    shop.set("SpeedPotion", enabled);
                }

                public static boolean getAnalyzerEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.ANALYZER_HOE);
                }

                public static void setAnalyzerEnabled(boolean enabled) {
                    shop.set("Analyzer", enabled);
                }

                public static boolean getCurseEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.CURSE_BIN);
                }

                public static void setCurseEnabled(boolean enabled) {
                    shop.set("Curse", enabled);
                }

                public static boolean getMilkEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.MILK);
                }

                public static void setMilkEnabled(boolean enabled) {
                    shop.set("Milk", enabled);
                }

                public static boolean getHealPotionEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.HEAL_POTION);
                }

                public static void setHealPotionEnabled(boolean enabled) {
                    shop.set("HealPotion", enabled);
                }

                public static boolean getCrackerEnabled() {
                    return !Config.getDisableItem_Villager().contains(ItemType.CRACKER);
                }

                public static void setCrackerEnabled(boolean enabled) {
                    shop.set("Cracker", enabled);
                }
            }*/
        }

    }

    public static void save() {
        try {
            conf.save(confFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum LanguageType {
        JAPANESE("japanese.yml"),
        ENGLISH("english.yml");

        private final String fileName;
        LanguageType(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
    }
}
