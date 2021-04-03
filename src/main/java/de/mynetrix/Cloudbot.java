package de.mynetrix;

import de.mynetrix.database.SQLiteDataSource;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Cloudbot {

    public static final Map<Long, String> PREFIXES = new HashMap<>();
    private static JDA jda;

    public static void main(String[] args) throws LoginException, SQLException {
        JDABuilder builder = JDABuilder.createDefault(Config.get("TOKEN"));
        SQLiteDataSource.getConnection();

        builder.enableIntents(GatewayIntent.GUILD_BANS);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES);
        builder.enableIntents(GatewayIntent.DIRECT_MESSAGES);

        builder.setActivity(Activity.playing("mit deinem Discord"));
        builder.addEventListeners(new Listener());


        jda = builder.build();

    }


    public static JDA getJda() {
        return jda;
    }
}
