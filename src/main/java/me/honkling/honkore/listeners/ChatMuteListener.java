package me.honkling.honkore.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.honkling.honkore.Honkore;
import me.honkling.honkore.lib.Utils;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatMuteListener implements Listener {

	private final Honkore plugin = Honkore.getInstance();

	@EventHandler
	public void onChatMessage(AsyncChatEvent e) {
		Player p = e.getPlayer();

		if(plugin.chatMuted && !p.hasPermission("honkore.bypasschatmute")) {
			e.setCancelled(true);
			String message = plugin.getConfig().getString("Messages.chat-muted");

			if (message == null) {
				plugin.getLogger().warning("Mute chat message has not been defined. Skipping...");
				return;
			}

			if (message.length() > 0) {
				p.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(message));
			}
		}
	}

}
