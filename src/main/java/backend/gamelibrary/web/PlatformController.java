package backend.gamelibrary.web;

import org.springframework.stereotype.Controller;

import backend.gamelibrary.domain.PlatformRepository;

@Controller
public class PlatformController {

    private PlatformRepository platformRepository;

    public PlatformController(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }
}
