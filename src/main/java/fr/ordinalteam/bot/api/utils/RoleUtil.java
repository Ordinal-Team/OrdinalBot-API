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

    public static final long ORDINAL_OWNER = 648988771919855617L;
    public static final long MODERATEUR = 655494297373900800L;
    public static final long PARTENAIRE = 655494295171891249L;
    public static final long VENDEUR = 654047518220615700L;
    public static final long AMI_IRL = 691693393713168565L;
    public static final long AMI = 654047330776907801L;
    public static final long DEVELOPPEUR = 655494298157973514L;
    public static final long GRAPHISITE = 655494813977804800L;
    public static final long DONATEUR = 685215407983820879L;
    public static final long SEPHIRA = 935613563358281758L;
    public static final long NIKO_NIKO_NII = 684504208438722578L;
    public static final long POT_DE_FLEUR = 809793612791545916L;
    public static final long RM_RF_NO_PRESERVE_ROOT = 821070098265669683L;
    public static final long BOOSTER = 659549976010948620L;
    public static final long GROS_RAT = 937419668979875840L;
    public static final long PING_MOI_INSULTE_MOI_VAS_Y = 864452485782306828L;
    public static final long SUCEUR_PRO = 877688320912670760L;
    public static final long LEARN_JAVA = 813468014313078824L;
    public static final long ZERO_CINQ_DE_QI = 736205916281503895L;
    public static final long MEMBRE = 654047191395991561L;

    public static boolean isOp(final Member member) {
        return member.getRoles().contains(member.getGuild().getRoleById(ORDINAL_OWNER));
    }

    /**
     * @param member Member
     * @return true if the member in parameter have a mod permission (role)
     */
    public static boolean hasModPermission(final Member member) {
        for (final Role roles : member.getRoles()) {
            for (final Role role : getModsRole(member.getGuild())) {
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
    public static List<Role> getModsRole(final Guild guild) {
        final List<Role> roles = new ArrayList<>();
        roles.add(guild.getRoleById(ORDINAL_OWNER));
        roles.add(guild.getRoleById(MODERATEUR));

        return roles;
    }
}
