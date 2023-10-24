package commands.dadJokes;

import Commands.Abstracts.SlashCommand;
import commands.DadMain;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;

public class StartJokes extends SlashCommand {
    public StartJokes() {
        name = "start_jokes";
        description = "Let me start telling you some jokes";
        options = new ArrayList<>();
        options.add(new OptionData(OptionType.INTEGER, "seconds","Number of seconds for each delay",false));
        options.add(new OptionData(OptionType.INTEGER, "minutes", "Number of minutes for each delay", false));
        options.add(new OptionData(OptionType.INTEGER,"hours","Number of hours for each delay", false));
    }

    @Override
    public void action(SlashCommandInteractionEvent event) {
        int delay = 0;
        try {
            delay += event.getOption("seconds").getAsInt() * DadJokes.SEC;
        } catch (NullPointerException e) {} // try
        try {
            delay += event.getOption("minutes").getAsInt() * DadJokes.MIN;
        } catch (NullPointerException e) {} // try
        try {
            delay += event.getOption("hours").getAsInt() * DadJokes.HOUR;
        } catch (NullPointerException e) {} // try
        if (delay == 0) {
            delay += DadJokes.MIN;
        }
        DadMain.dadJokes.start(event.getGuild(), delay);
        event.reply("Glad you still like my jokes!!!").setEphemeral(true).queue();
    }
}
