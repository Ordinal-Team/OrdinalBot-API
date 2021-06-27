package fr.ordinalteam.bot.api.utils;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Arinonia 19/06/2021
 */
public class RoleUtil {

    public static boolean isOp(Member member) {
        return member.getRoles().contains(member.getGuild().getRoleById(648988771919855617L));
    }

    /**
     * @param member Member
     * @return true if the member in parameter have a mod permission (role)
     */
    public static boolean hasModPermission(Member member) {
        for (Role roles : member.getRoles()) {
            for (Role role : getModsRole(member.getGuild())) {
                if (roles == role) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param guild server
     * @return all roles for moderator
     */
    public static List<Role> getModsRole(Guild guild) {
        List<Role> roles = new ArrayList<>();
        roles.add(guild.getRoleById(655494296035786752L));
        roles.add(guild.getRoleById(655494296799150110L));
        roles.add(guild.getRoleById(655494297373900800L));
        roles.add(guild.getRoleById(648988771919855617L));
        return roles;
    }
}
