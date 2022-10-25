package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emyo.jamin.jej.crefoilo.entity.SnsInfo;
import emyo.jamin.jej.crefoilo.repository.dslmodel.SnsInfoDsl;

@Repository
public interface SnsInfoRepository extends JpaRepository<SnsInfo, Long>, SnsInfoDsl {

}
