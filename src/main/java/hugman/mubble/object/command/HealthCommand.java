package hugman.mubble.object.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;

import java.util.Collection;

public class HealthCommand {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(
				LiteralArgumentBuilder.<ServerCommandSource>literal("health")
						.requires((source) ->
						{
							return source.hasPermissionLevel(2);
						})
						.then(CommandManager.literal("add")
								.then(CommandManager.argument("targets", EntityArgumentType.entities())
										.then(CommandManager.argument("amount", FloatArgumentType.floatArg())
												.executes((source) ->
												{
													return setHealth(source.getSource(), EntityArgumentType.getEntities(source, "targets"), FloatArgumentType.getFloat(source, "amount"), true);
												})
										)))
						.then(CommandManager.literal("set")
								.then(CommandManager.argument("targets", EntityArgumentType.entities())
										.then(CommandManager.argument("amount", FloatArgumentType.floatArg(0.0f))
												.executes((source) ->
												{
													return setHealth(source.getSource(), EntityArgumentType.getEntities(source, "targets"), FloatArgumentType.getFloat(source, "amount"), false);
												})
										)))
		);
	}

	private static int setHealth(ServerCommandSource source, Collection<? extends Entity> targets, float amount, boolean sum) {
		int finalTargetAmount = 0;
		for(Entity entity : targets) {
			if(entity instanceof LivingEntity) {
				finalTargetAmount++;
				LivingEntity livingEntity = (LivingEntity) entity;
				if(sum == true) {
					if(amount > 0.0F) {
						livingEntity.heal(amount);
					}
					else if(amount < 0.0F) {
						livingEntity.damage(DamageSource.OUT_OF_WORLD, amount * -1.0f);
					}
				}
				else {
					if(livingEntity.getHealth() > 0.0F) {
						if(amount == 0.0F) {
							livingEntity.kill();
						}
						else {
							livingEntity.setHealth(amount);
						}
					}
				}
			}
		}
		final String parameter;
		if(sum == true) {
			parameter = "add";
		}
		else {
			parameter = "set";
		}
		if(targets.size() == 1) {
			source.sendFeedback(new TranslatableText("commands.health." + parameter + ".success.single", amount, targets.iterator().next().getDisplayName()), true);
		}
		else {
			source.sendFeedback(new TranslatableText("commands.health." + parameter + ".success.multiple", amount, finalTargetAmount), true);
		}
		return finalTargetAmount;
	}
}