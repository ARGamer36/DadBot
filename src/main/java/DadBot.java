import Main.BotDriver;
import net.dv8tion.jda.api.entities.Activity;

public class DadBot extends BotDriver {
    @Override
    protected void setup() {
        VERSION = "1.0";
        activity = Activity.watching("Test Bot");
    }
}
