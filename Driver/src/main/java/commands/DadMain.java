package commands;

import main.MainCommands;
import commands.dadJokes.*;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;

public class DadMain extends MainCommands {
    public DadJokes dadJokes;
    public DadMain(String version) {
        super(version);
        prefixCommands.add(new GiveJoke());
        dadJokes = new DadJokes();
        slashCommands.add(new StartJokes(dadJokes));
        slashCommands.add(new StopJokes(dadJokes));
        slashCommands.add(new ResetJokes(dadJokes));
        slashCommands.add(new SetChannel(dadJokes));
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        super.onGuildReady(event);
        dadJokes.start(event);
    }
}
