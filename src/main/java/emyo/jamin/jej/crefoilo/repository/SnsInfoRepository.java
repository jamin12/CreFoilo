package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emyo.jamin.jej.crefoilo.entity.SnsInfo;
import emyo.jamin.jej.crefoilo.repository.dslmodel.SnsInfoDsl;

public interface SnsInfoRepository extends JpaRepository<SnsInfo, Long>, SnsInfoDsl {

}
