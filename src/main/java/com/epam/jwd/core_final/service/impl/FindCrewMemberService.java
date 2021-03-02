package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.service.CrewService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class FindCrewMemberService implements CrewService {
    private final Collection<CrewMember> CREW_MEMBER_CASH  =
            NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class);

    private static FindCrewMemberService instance;

    public static FindCrewMemberService getInstance() {
        if (instance == null) {
            instance = new FindCrewMemberService();
        }
        return instance;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return new ArrayList<>(CREW_MEMBER_CASH);
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {

        return null;
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        return Optional.empty();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        return null;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {
        if (crewMember.getReadyForNextMissions() == true) {
            crewMember.crewMemberIsNotReadeForNextMissions();
        }
    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws RuntimeException {
        boolean isDuplicate = CREW_MEMBER_CASH.stream()
                .noneMatch(member -> member.getName().equals(crewMember.getName()));
        // add duplicate check

        if (isDuplicate) {
            CREW_MEMBER_CASH.add(crewMember);
        }
        return crewMember;
    }
}
