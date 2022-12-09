package emyo.jamin.jej.crefoilo.service;

import emyo.jamin.jej.crefoilo.dto.AboutmeDto;

public interface AboutmeService {
    AboutmeDto findAboutme(Long portfolioId);

    AboutmeDto findAboutme(Long portfolioId, String userId);

    String createAboutMe(Long portfolioId, String userId, AboutmeDto aboutmeDto);

}