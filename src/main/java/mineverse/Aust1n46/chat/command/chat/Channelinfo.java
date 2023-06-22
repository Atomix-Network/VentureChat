package mineverse.Aust1n46.chat.command.chat;

import mineverse.Aust1n46.chat.channel.ChatChannel;
import mineverse.Aust1n46.chat.channel.ChatFormat;
import mineverse.Aust1n46.chat.utilities.Format;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Iterator;
import java.util.Map;

public class Channelinfo extends Command {
	public Channelinfo() {
		super("channelinfo");
	}

	@Override
	public boolean execute(CommandSender sender, String command, String[] args) {
		if (sender.hasPermission("venturechat.channelinfo")) {
			if (args.length < 1) {
				sender.sendMessage(ChatColor.RED + "Invalid command: /channelinfo [channel]");
				return true;
			}
			ChatChannel chname = ChatChannel.getChannel(args[0]);
			if (chname == null) {
				sender.sendMessage(ChatColor.RED + "Invalid channel: " + args[0]);
				return true;
			}
			if (chname.hasPermission()) {
				if (!sender.hasPermission(chname.getPermission())) {
					sender.sendMessage(ChatColor.RED + "You do not have permission to look at this channel.");
					return true;
				}
			}
			sender.sendMessage(ChatColor.GOLD + "Channel: " + chname.getColor() + chname.getName());
			sender.sendMessage(ChatColor.GOLD + "Alias: " + chname.getColor() + chname.getAlias());
			sender.sendMessage(ChatColor.GOLD + "Color: " + chname.getColor() + chname.getColorRaw());
			sender.sendMessage(ChatColor.GOLD + "ChatColor: " + (chname.getChatColor().equalsIgnoreCase("None") ? Format.DEFAULT_COLOR_CODE : chname.getChatColor())
					+ chname.getChatColorRaw());
			if (chname.hasPermission()) {
				sender.sendMessage(ChatColor.GOLD + "Permission: " + chname.getColor() + chname.getPermission());
			} else {
				sender.sendMessage(ChatColor.GOLD + "Permission: " + chname.getColor() + "None");
			}
			if (chname.hasSpeakPermission()) {
				sender.sendMessage(ChatColor.GOLD + "Speak Permission: " + chname.getColor() + chname.getSpeakPermission());
			} else {
				sender.sendMessage(ChatColor.GOLD + "Speak Permission: " + chname.getColor() + "None");
			}
			sender.sendMessage(ChatColor.GOLD + "Autojoin: " + chname.getColor() + chname.getAutojoin());
			sender.sendMessage(ChatColor.GOLD + "Default: " + chname.getColor() + chname.hasDistance());
			if (!chname.hasDistance() || chname.getBungee()) {
				sender.sendMessage(ChatColor.GOLD + "Distance: " + ChatColor.RED + "N/A");
			} else {
				sender.sendMessage(ChatColor.GOLD + "Distance: " + chname.getColor() + chname.getDistance());
			}
			if (!chname.hasCooldown()) {
				sender.sendMessage(ChatColor.GOLD + "Cooldown: " + ChatColor.RED + "N/A");
			} else {
				sender.sendMessage(ChatColor.GOLD + "Cooldown: " + chname.getColor() + chname.getCooldown());
			}
			sender.sendMessage(ChatColor.GOLD + "Bungeecord: " + chname.getColor() + chname.getBungee());
			sender.sendMessage(generateFormatsString(chname));
			return true;
		} else {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command.");
			return true;
		}
	}

	/**
	 * Generates and returns a String displaying all the formats of the passed channel.
	 *
	 * @param channel Channel whose formats will be displayed.
	 * @return String displaying all the formats of the passed channel.
	 */
	private String generateFormatsString(ChatChannel channel) {
		StringBuilder builder = new StringBuilder(ChatColor.GOLD + "Formats:\n");
		Iterator<Map.Entry<String, ChatFormat>> entryIterator = channel.getFormats().entrySet().iterator();
		while (entryIterator.hasNext()) {
			Map.Entry<String, ChatFormat> entry = entryIterator.next();
			builder.append("  ").append(ChatColor.YELLOW).append(entry.getKey()).append("\n");

			ChatFormat chatFormat = entry.getValue();
			builder.append("    ").append(ChatColor.BLUE).append("Priority: ").append(chatFormat.getPriority());
			builder.append("    ").append(ChatColor.GREEN).append("Format: ").append(chatFormat.getFormat());
			if (entryIterator.hasNext()) {
				builder.append("\n");
			}
		}
		return builder.toString();
	}
}
