package com.fletchplugins.perworldcommands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class PerWorldTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length==1){
            List<String> worldNames=new ArrayList<>();
            World[] worlds=new World[Bukkit.getServer().getWorlds().size()];
            Bukkit.getServer().getWorlds().toArray(worlds);
            for(int i=0; i<worlds.length; i++){
                worldNames.add(worlds[i].getName());
            }
            return worldNames;
        }


        return null;
    }
}
