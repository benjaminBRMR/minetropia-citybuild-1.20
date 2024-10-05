package net.citybuild.backend.manager;

import eu.koboo.en2do.Credentials;
import eu.koboo.en2do.MongoManager;
import lombok.Getter;
import net.citybuild.backend.cases.CaseRepository;
import net.citybuild.backend.invite.InviteRepository;
import net.citybuild.backend.location.LocationRepository;
import net.citybuild.backend.user.UserRepository;
import net.citybuild.backend.user.creatorcode.CreatorCodeRepository;

@Getter
public class DBManager {

    private UserRepository userRepository;
    private CaseRepository caseRepository;
    private LocationRepository locationRepository;
    private CreatorCodeRepository creatorCodeRepository;
    private InviteRepository inviteRepository;


    private MongoManager mongoManager;

    public DBManager() {

        try {
            mongoManager = new MongoManager(Credentials.of("mongodb://minetropia:passwort@ip:port", "citybuild"));
            userRepository = mongoManager.create(UserRepository.class);
            caseRepository = mongoManager.create(CaseRepository.class);
            locationRepository = mongoManager.create(LocationRepository.class);
            creatorCodeRepository = mongoManager.create(CreatorCodeRepository.class);
            inviteRepository = mongoManager.create(InviteRepository.class);

            System.out.println("[DB] Connected to database.");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
