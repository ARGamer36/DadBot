package commands.dadJokes;

import Commands.Abstracts.SlashCommand;
import commands.DadMain;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class StopJokes extends SlashCommand {
    public StopJokes() {
        name = "stop_jokes";
        description = "Hurt your father's heart by asking him to stop saying jokes";
    }

    @Override
    public void action(SlashCommandInteractionEvent event) {
        DadMain.dadJokes.stop();
        event.reply("I understand kiddo. I knew you wouldn't love the jokes forever.").setEphemeral(true).queue();
    }
}
