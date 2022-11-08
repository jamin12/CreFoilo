package emyo.jamin.jej.crefoilo.service;

import emyo.jamin.jej.crefoilo.dto.AboutmeDto;
import emyo.jamin.jej.crefoilo.entity.AboutMe;

public interface AboutmeService {
    AboutmeDto findAboutme(Long portfolioId);
}
