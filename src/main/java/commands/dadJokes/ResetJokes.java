package commands.dadJokes;

import Commands.Abstracts.SlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class ResetJokes extends SlashCommand {
    DadJokes dadJokes;
    public ResetJokes(DadJokes dJ) {
        name = "reset_jokes";
        description = "Dad will bring back the old reliables";
        dadJokes = dJ;
    }

    @Override
    public void action(SlashCommandInteractionEvent event) {
        dadJokes.resetDadJokesList();
        event.reply("I understand kiddo").setEphemeral(true).queue();
    }
}
