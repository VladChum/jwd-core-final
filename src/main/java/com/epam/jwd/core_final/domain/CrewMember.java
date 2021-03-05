package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions;

    public CrewMember(String name, Role role, Rank rank) {
        super(name);
        this.role = role;
        this.rank = rank;
        this.isReadyForNextMissions = true;
    }

    @Override
    public String toString() {
        return getId() + "\t" +
                getName() + "\t\t\t\t" +
                rank + "\t\t\t\t" +
                isReadyForNextMissions + "\t" +
                role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Rank getRank() {
        return rank;
    }

    public void setReadyForNextMissions(Boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void crewMemberIsNotReadeForNextMissions() {
        isReadyForNextMissions = false;
    }
}
