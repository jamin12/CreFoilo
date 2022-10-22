package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import emyo.jamin.jej.crefoilo.entity.SnsInfo;

public interface SnsInfoDsl {
    List<SnsInfo> findByEmail(String email);
}
