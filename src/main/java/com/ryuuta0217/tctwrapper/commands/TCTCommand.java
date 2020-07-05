package com.ryuuta0217.tctwrapper.commands;

import com.ryuuta0217.tctwrapper.config.TCTConfiguration;
import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.CommandExecutor;
import io.github.jorelali.commandapi.api.arguments.*;
import io.github.jorelali.commandapi.api.exceptions.WrapperCommandSyntaxException;
import org.bukkit.command.CommandSender;

import java.util.LinkedHashMap;

public class TCTCommand implements CommandExecutor {
    private final static String NAME = "tct";

    public static void register() {
        CommandAPI api = CommandAPI.getInstance();

        for(Mode commandMode : Mode.values()) {
            // mode loop (settings)

            for(ProcessMode processMode : ProcessMode.values()) {
                // process mode loop (get / set)

                for(Config config : Config.values()) {
                    // config loop (language, game_setting, game_mode_setting)

                    // set up base arguments
                    LinkedHashMap<String, Argument> arguments = new LinkedHashMap<String, Argument>() {{
                        put("コマンド", new LiteralArgument(commandMode.name().toLowerCase()));
                        put("処理モード", new LiteralArgument(processMode.name().toLowerCase()));
                        put("設定カテゴリ", new LiteralArgument(config.name().toLowerCase()));
                    }};

                    // Arguments: /tct <commandMode> <processMode> language
                    // Arguments: /tct <commandMode> <processMode> console_command_to_winner
                    // Arguments: /tct <commandMode> <processMode> console_command_to_loser
                    if(config == Config.LANGUAGE || config == Config.CONSOLE_COMMAND_TO_WINNER || config == Config.CONSOLE_COMMAND_TO_LOSER) {
                        if(processMode == ProcessMode.SET) {
                            if(config == Config.CONSOLE_COMMAND_TO_WINNER || config == Config.CONSOLE_COMMAND_TO_LOSER) {
                                for(CommandListProcessMode listProcessMode : CommandListProcessMode.values()) {
                                    arguments.put("リスト処理モード", new LiteralArgument(listProcessMode.name().toLowerCase()));
                                    arguments.put("対象コマンド", new GreedyStringArgument());

                                    // TODO 設定されているコマンドをsuggestしたいところだが、方法がわからないのでパス
                                    /*if(listProcessMode == CommandListProcessMode.REMOVE) {
                                        if (config == Config.CONSOLE_COMMAND_TO_WINNER) {
                                            arguments.put("対象コマンド", new DynamicSuggestedStringArgument(() -> TCTConfiguration.getConsoleCommandToWinner().toArray(new String[0])));
                                        } else {
                                            arguments.put("対象コマンド", new DynamicSuggestedStringArgument(() -> TCTConfiguration.getConsoleCommandToLoser().toArray(new String[0])));
                                        }
                                    }*/

                                    api.register("tct", arguments, new TCTCommand(commandMode, processMode, config, listProcessMode));
                                }
                            } else {
                                for(TCTConfiguration.LanguageType language : TCTConfiguration.LanguageType.values()) {
                                    arguments.put("言語", new LiteralArgument(language.name().toLowerCase()));
                                    api.register("tct", arguments, new TCTCommand(commandMode, processMode, config, language));
                                }
                            }
                        } else {
                            api.register("tct", arguments, new TCTCommand(commandMode, processMode, config));
                        }
                        continue;
                    }

                    // Arguments: /tct <commandMode> <processMode> game_setting
                    if(config == Config.GAME_SETTING) {
                        for(GameSettingSubConfig gameSetting : GameSettingSubConfig.values()) {
                            arguments.put("設定", new LiteralArgument(gameSetting.name().toLowerCase()));

                            if(processMode == ProcessMode.SET) {
                                arguments.put("値", new IntegerArgument(0));
                            }

                            api.register("tct", arguments, new TCTCommand(commandMode, processMode, config, gameSetting));
                        }
                        continue;
                    }

                    // Arguments: /tct <commandMode> <processMode> game_mode_setting
                    if(config == Config.GAME_MODE_SETTING) {
                        for(GameModeSettingSubConfig gameModeSetting : GameModeSettingSubConfig.values()) {
                            arguments.put("設定", new LiteralArgument(gameModeSetting.name().toLowerCase()));

                            if(processMode == ProcessMode.SET) {
                                arguments.put("値", new BooleanArgument());
                            }

                            api.register("tct", arguments, new TCTCommand(commandMode, processMode, config, gameModeSetting));
                        }
                        continue;
                    }

                    // Arguments: /tct <commandMode> <processMode> custom_mode_setting
                    if(config == Config.CUSTOM_MODE_SETTING) {
                        for(CustomModeSettingSubConfig customModeSetting : CustomModeSettingSubConfig.values()) {
                            arguments.put("設定", new LiteralArgument(customModeSetting.name().toLowerCase()));

                            if(processMode == ProcessMode.SET) {
                                if(customModeSetting == CustomModeSettingSubConfig.ENABLE) {
                                    arguments.put("値", new BooleanArgument());
                                } else {
                                    arguments.put("値", new IntegerArgument(0));
                                }
                            }

                            api.register("tct", arguments, new TCTCommand(commandMode, processMode, config, customModeSetting));
                        }
                        continue;
                    }

                    // Arguments: /tct <commandMode> <processMode> role
                    if(config == Config.ROLE) {
                        for(Roles role : Roles.values()) {
                            arguments.put("役職", new LiteralArgument(role.name().toLowerCase()));

                            // ^ Arguments: /tct <commandMode> <processMode> role <role>
                            for(RoleSubConfig roleSubConfig : RoleSubConfig.values()) {
                                // loop start
                                arguments.put("設定", new LiteralArgument(roleSubConfig.name().toLowerCase()));

                                // ^ Arguments: /tct <commandMode> <processMode> role <role> <roleSubConfig>
                                if(roleSubConfig == RoleSubConfig.SHOP) {
                                    for(ShopItems item : getShopAvailableItemList(role)) {
                                        arguments.put("アイテム", new LiteralArgument(item.name().toLowerCase()));

                                        // ^ Arguments: /tct <commandMode> <processMode> role <role> shop <item>
                                        if(processMode == ProcessMode.SET) {
                                            arguments.put("有効", new BooleanArgument());
                                            // ^ Arguments: /tct <commandMode> <processMode> role <role> shop <item> <Boolean>
                                        }

                                        // ^ Arguments: /tct <commandMode> <processMode> role <role> shop <item> [Boolean]
                                        api.register("tct", arguments, new TCTCommand(commandMode, processMode, config, role, roleSubConfig, item));
                                    }
                                } else {
                                    if(processMode == ProcessMode.SET) {
                                        arguments.put("値", new IntegerArgument(0));
                                        // ^ Arguments: /tct <commandMode> <processMode> role <role> default_coin <Integer>
                                    }

                                    // ^ Arguments: /tct <commandMode> <processMode> role <role> default_coin [Integer]
                                    api.register("tct", arguments, new TCTCommand(commandMode, processMode, config, role, roleSubConfig));

                                    arguments.remove("値");
                                    // ^ Arguments: /tct <commandMode> <processMode> role <role> default_coin

                                    // back to loop start
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private final Mode commandMode;
    private final ProcessMode processMode ;
    private final Config config;
    private GameSettingSubConfig gameConfig = null;
    private GameModeSettingSubConfig gameModeConfig = null;
    private CustomModeSettingSubConfig customModeConfig = null;
    private CommandListProcessMode listProcessMode = null;
    private TCTConfiguration.LanguageType language = null;
    private Roles role = null;
    private RoleSubConfig roleSubConfig = null;
    private ShopItems item = null;

    public TCTCommand(Mode commandMode, ProcessMode processMode, Config config) {
        this.commandMode = commandMode;
        this.processMode = processMode;
        this.config = config;
    }

    public TCTCommand(Mode commandMode, ProcessMode processMode, Config config, GameSettingSubConfig gameConfig) {
        this(commandMode, processMode, config);
        this.gameConfig = gameConfig;
    }

    public TCTCommand(Mode commandMode, ProcessMode processMode, Config config, GameModeSettingSubConfig gameModeConfig) {
        this(commandMode, processMode, config);
        this.gameModeConfig = gameModeConfig;
    }

    public TCTCommand(Mode commandMode, ProcessMode processMode, Config config, CustomModeSettingSubConfig customModeConfig) {
        this(commandMode, processMode, config);
        this.customModeConfig = customModeConfig;
    }

    public TCTCommand(Mode commandMode, ProcessMode processMode, Config config, CommandListProcessMode listProcessMode) {
        this(commandMode, processMode, config);
        this.listProcessMode = listProcessMode;
    }

    public TCTCommand(Mode commandMode, ProcessMode processMode, Config config, TCTConfiguration.LanguageType language) {
        this(commandMode, processMode, config);
        this.language = language;
    }

    public TCTCommand(Mode commandMode, ProcessMode processMode, Config config, Roles role, RoleSubConfig roleSubConfig) {
        this(commandMode, processMode, config);
        if(roleSubConfig == RoleSubConfig.SHOP) throw new IllegalArgumentException("ShopItemsを引数に渡す必要があります");
        this.role = role;
        this.roleSubConfig = roleSubConfig;
    }

    public TCTCommand(Mode commandMode, ProcessMode processMode, Config config, Roles role, RoleSubConfig roleSubConfig, ShopItems item) {
        this(commandMode, processMode, config);
        this.role = role;
        this.roleSubConfig = roleSubConfig;
        this.item = item;
    }

    @Override
    public void run(CommandSender commandSender, Object[] objects) throws WrapperCommandSyntaxException {

    }

    private enum Mode {
        SETTINGS
    }

    private enum ProcessMode {
        GET,
        SET
    }

    private enum Config {
        LANGUAGE,
        GAME_SETTING,
        GAME_MODE_SETTING,
        CUSTOM_MODE_SETTING,
        CONSOLE_COMMAND_TO_WINNER,
        CONSOLE_COMMAND_TO_LOSER,
        ROLE
    }

    private enum CommandListProcessMode {
        ADD,
        REMOVE
    }

    private enum GameSettingSubConfig {
        DEFAULT_HEALTH,
        PREPARATION_TIME,
        GIVE_COIN_TIME,
        EXTEND_TIME,
        DEFAULT_END_TIME
    }

    private enum GameModeSettingSubConfig {
        JAPANESE_CHAT_MODE,
        INFINITE_TIME_MODE,
        SELF_DECLARED_MODE
    }

    private enum CustomModeSettingSubConfig {
        ENABLE,
        WOLF_COUNT,
        DETECTIVE_COUNT,
        DOCTOR_COUNT,
        MADMAN_COUNT,
        FOX_COUNT
    }

    private enum Roles {
        VILLAGER,
        DETECTIVE,
        WOLF,
        DOCTOR,
        MADMAN,
        FOX
    }

    private enum RoleSubConfig {
        DEFAULT_COIN,
        SHOP
    }

    private enum ShopItems {
        STONE_SWORD,
        IRON_SWORD,
        DETECTIVE_SWORD,
        WOLF_SWORD,
        BOW,
        ANALYZER,
        HELMET,
        CURSE,
        TNT,
        COMPASS,
        SNOW_BALL,
        FIRE,
        MILK,
        SEEDS,
        TELEPORT_DIAMOND,
        CREEPER,
        DUMMY,
        ANTI_EXPLOSION,
        SPEED_POTION,
        HEAL_POTION,
        HEAL_STATION,
        CRACKER
    }

    private static ShopItems[] getShopAvailableItemList(Roles role) {
        switch (role) {
            case DETECTIVE:
                return new ShopItems[]{ShopItems.IRON_SWORD, ShopItems.DETECTIVE_SWORD, ShopItems.HELMET, ShopItems.HEAL_STATION, ShopItems.BOW, ShopItems.SPEED_POTION, ShopItems.SEEDS, ShopItems.ANTI_EXPLOSION, ShopItems.HEAL_POTION, ShopItems.CRACKER};
            case WOLF:
                return new ShopItems[]{ShopItems.STONE_SWORD, ShopItems.TNT, ShopItems.COMPASS, ShopItems.BOW, ShopItems.SNOW_BALL, ShopItems.FIRE, ShopItems.WOLF_SWORD, ShopItems.SPEED_POTION, ShopItems.TELEPORT_DIAMOND, ShopItems.ANALYZER, ShopItems.CURSE, ShopItems.CREEPER, ShopItems.DUMMY, ShopItems.HEAL_POTION, ShopItems.CRACKER};
            default:
                return new ShopItems[]{ShopItems.STONE_SWORD, ShopItems.BOW, ShopItems.SPEED_POTION, ShopItems.ANALYZER, ShopItems.CURSE, ShopItems.MILK, ShopItems.HEAL_POTION, ShopItems.CRACKER};
        }
    }
}
