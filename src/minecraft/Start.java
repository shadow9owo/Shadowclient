import java.util.Arrays;
import net.minecraft.client.main.Main;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class Start {
    private static final String APPLICATION_ID = "1117424093080338453";
    private static final String LARGE_ICON_NAME = "large";
    private static final String DOWNLOAD_LINK = "https://github.com/shadow9owo/Shadowclient";

    public static void initialize() {
        // Initialize Discord RPC
        DiscordRPC.discordInitialize(APPLICATION_ID, null, true);
        updatePresence("Playing Minecraft", "Download at: " + DOWNLOAD_LINK);
    }

    public static void updatePresence(String state, String details) {
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.state = state;
        presence.details = details;
        presence.largeImageKey = LARGE_ICON_NAME;

        DiscordRPC.discordUpdatePresence(presence);
    }

    public static void shutdown() {
        // Shutdown Discord RPC
        DiscordRPC.discordShutdown();
    }

    public static void main(String[] args) {
        initialize();

        Main.main(concat(new String[] {"--version", "mcp", "--accessToken", "0", "--assetsDir", "assets", "--assetIndex", "1.8", "--userProperties", "{}"}, args));

        shutdown();
    }

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
