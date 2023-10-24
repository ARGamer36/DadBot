import Main.MainCommands;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;

public class DadMain extends MainCommands {
    DadJokes dadJokes = new DadJokes();
    public DadMain(String version) {
        super(version);
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        super.onGuildReady(event);
        dadJokes.start(event);
    }
}
