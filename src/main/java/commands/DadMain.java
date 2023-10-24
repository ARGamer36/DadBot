package commands;

import Main.MainCommands;
import commands.dadJokes.DadJokes;
import commands.dadJokes.GiveJoke;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;

public class DadMain extends MainCommands {
    DadJokes dadJokes;
    public DadMain(String version) {
        super(version);
        prefixCommands.add(new GiveJoke());
        dadJokes = new DadJokes();
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        super.onGuildReady(event);
        dadJokes.start(event);
    }
}
