package keython.mandalart.mandalart;

import keython.mandalart.domain.MandalArt;

import java.util.List;
import java.util.Optional;

public interface MandalArtRepository {

    //MandalArt 생성
    Long createMandal(MandalArt mandalArt);


    //조회

    //사용자의 현재 mandalArt 조회
    Optional<MandalArt> findCurrentMandalArtByUserId(Long userId);

    //사용자의 전체 mandalArt 조회
    List<MandalArt> findAllMandalArtsByUserId(Long userId);

    //id로 조회
    MandalArt findOne(Long mandalArtId);





}
