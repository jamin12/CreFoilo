package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;
import java.util.Optional;

import emyo.jamin.jej.crefoilo.entity.SnsInfo;
import emyo.jamin.jej.crefoilo.entity.User;

public interface SnsInfoDsl {
    List<SnsInfo> getUserByEmail(String email);
}
