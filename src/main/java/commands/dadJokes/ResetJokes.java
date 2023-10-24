package commands.dadJokes;

import Commands.Abstracts.SlashCommand;
import commands.DadMain;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class ResetJokes extends SlashCommand {
    public ResetJokes() {
        name = "reset_jokes";
        description = "Dad will bring back the old reliables";
    }

    @Override
    public void action(SlashCommandInteractionEvent event) {
        DadMain.dadJokes.resetDadJokesList();
        event.reply("I understand kiddo").setEphemeral(true).queue();
    }
}
