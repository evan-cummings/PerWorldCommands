package com.fletchplugins.perworldcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandClass implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("pw")){
            //pw {world} {command to run}
            if(args.length<2){
                sender.sendMessage("Not enough arguments!");
            }
            else{
                try{
                    World wantedWorld;
                    if(args[0].equalsIgnoreCase("this")&&sender instanceof Player){
                        Player p=(Player) sender;
                        wantedWorld=p.getWorld();
                    }
                    else{
                        wantedWorld=Bukkit.getServer().getWorld(args[0]);
                    }
                    UUID uuid=wantedWorld.getUID();
                    String uuidLeast=uuid.getLeastSignificantBits()+"L";
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    //String fullCommand="/execute in "+wantedWorld.getName()+" run ";
                    String fullCommand="";
                    String nbtTag="nbt={WorldUUIDLeast:"+uuidLeast+"}";

                    for (int i = 1; i < args.length; i++){
                        if(args[i].contains("@a")||args[i].contains("@p")||args[i].contains("@e")){
                            if(args[i].equalsIgnoreCase("@a")||args[i].equalsIgnoreCase("@p")||args[i].equalsIgnoreCase("@e")){
                                fullCommand=fullCommand+args[i]+"["+nbtTag+"] ";
                            }
                            else{
                                fullCommand=fullCommand+args[i].substring(0,args[i].indexOf("[")+1)+nbtTag+","+args[i].substring(args[i].indexOf("[")+1)+" ";
                            }
                        }
                        else{
                            fullCommand=fullCommand+args[i]+" ";
                        }
                    }
                    if(sender instanceof Player){
                        Player player = (Player) sender;
                        if(player.hasPermission("perworldcommands.runcommand")){
                            Bukkit.dispatchCommand(sender,fullCommand);
                        }
                        else{
                            sender.sendMessage(ChatColor.RED+"You don't have permission!");
                        }
                    }
                    else{
                        Bukkit.dispatchCommand(sender,fullCommand);
                    }


                }
                catch (Exception e){
                    sender.sendMessage(ChatColor.RED+"Not a valid world!");
                }
            }
        }


        return true;
    }
}
