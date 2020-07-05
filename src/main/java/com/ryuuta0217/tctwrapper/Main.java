package com.ryuuta0217.tctwrapper;

import com.ryuuta0217.tctwrapper.commands.TCTCommand;
import com.ryuuta0217.tctwrapper.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    public static void main(String[] args) {
        // TODO pluginsフォルダーに入れることを案内する等 GUIで
        // TODO できればpluginsフォルダー、サーバールートフォルダーを指定することで自動コピーできるとよい
        // TODO サーバールートの判定方法はeula.txtやspigot.yml/bukkit.yml/paper.ymlなどいろいろある
    }

    @Override
    public void onLoad() {
        getLogger().info(ChatColor.GREEN + "TCT Wrapper successfully loaded.");
    }

    @Override
    public void onEnable() {
        Logger.init(this);
        getLogger().info(ChatColor.GREEN + "TCT Wrapper enabled, hello!");
        if(org.bitbucket.jack_basukeraihu.TroubleInCrafterTown.tct.Main.plugin != null && org.bitbucket.jack_basukeraihu.TroubleInCrafterTown.tct.Main.plugin.isEnabled()) {
            getLogger().info(ChatColor.GREEN + "TCT Hook ok :)");
        }
        Bukkit.getPluginManager().registerEvents(this, this);
        TCTCommand.register();
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.RED + "TCT Wrapper disabled, goodbye!");
    }
}
