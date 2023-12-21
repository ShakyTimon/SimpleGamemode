package com.shakytimon.simplegamemode;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class CommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] commandArgs) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        GameMode gamemode;
        String gamemodeString;
        if (Objects.equals(commandLabel, "gms")) {
            gamemode = GameMode.SURVIVAL;
            gamemodeString = "survival";
        } else if (Objects.equals(commandLabel, "gmc")) {
            gamemode = GameMode.CREATIVE;
            gamemodeString = "creative";
        } else {
            commandSender.sendMessage(ChatColor.RED + "Invalid command.");
            return true;
        }

        Player player = (Player) commandSender;
        switch (commandArgs.length) {
            case 0:
                changePlayerGameMode(player, gamemode, gamemodeString);
                break;
            case 1:
                handleSingleArgument(player, commandArgs[0], gamemode, gamemodeString);
                break;
            default:
                player.sendMessage(ChatColor.RED + "Usage: /" + commandLabel + " [player] | *");
                break;
        }
        return true;
    }

    private void changePlayerGameMode(Player player, GameMode gamemode, String gamemodeString) {
        player.setGameMode(gamemode);
        player.sendMessage(ChatColor.GREEN + "Set gamemode to " + gamemodeString + ".");
    }

    private void handleSingleArgument(Player player, String arg, GameMode gameMode, String gmStr) {
        if (arg.equals("*")) {
            for (Player p : player.getServer().getOnlinePlayers()) {
                changePlayerGameMode(p, gameMode, gmStr);
            }
            player.sendMessage(ChatColor.GREEN + "Set gamemode of all players to " + gmStr + ".");
        } else {
            Player targetPlayer = player.getServer().getPlayer(arg);
            if (targetPlayer != null) {
                changePlayerGameMode(targetPlayer, gameMode, gmStr);
                player.sendMessage(ChatColor.GREEN + "Set gamemode of " + targetPlayer.getName() + " to " + gmStr + ".");
            } else {
                player.sendMessage(ChatColor.RED + "Player not found!");
            }
        }
    }
}