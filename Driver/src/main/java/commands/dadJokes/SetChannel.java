package commands.dadJokes;

import commands.abstracts.SlashCommand;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;

public class SetChannel extends SlashCommand {
    DadJokes dadJokes;
    public SetChannel(DadJokes dJ) {
        name = "set_channel";
        description = "Give Dad a crowd";
        dadJokes = dJ;
        options = new ArrayList<>();
        options.add(new OptionData(OptionType.CHANNEL, "channel", "New channel id", true));
    }

    @Override
    public void action(SlashCommandInteractionEvent event) {
        Channel channel = event.getOption("channel").getAsChannel();
        dadJokes.setJokeChannel(event.getGuild(), channel.getId());
        event.reply("Channel set").setEphemeral(true).queue();
    }
}
