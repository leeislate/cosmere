/*
 * File updated ~ 19 - 11 - 2023 ~ Leaf
 */

package leaf.cosmere.aviar.common.commands.subcommands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import leaf.cosmere.api.text.TextHelper;
import leaf.cosmere.common.commands.subcommands.ModCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;

public class AviarCommand extends ModCommand
{
	public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher)
	{
		return Commands.literal("aviarTest")
				.requires(context -> context.hasPermission(2))
				.executes(AviarCommand::aviarTest)
				.then(Commands.argument("target", EntityArgument.players())
						.requires(context -> context.hasPermission(2))
						.executes(AviarCommand::aviarTest));
	}

	private static int aviarTest(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
	{
		Collection<ServerPlayer> players = getPlayers(context, 3);

		for (ServerPlayer player : players)
		{
			player.displayClientMessage(TextHelper.createText("Test Received"), false);
		}

		return SINGLE_SUCCESS;
	}

	@Override
	public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
	{
		return SINGLE_SUCCESS;
	}

}