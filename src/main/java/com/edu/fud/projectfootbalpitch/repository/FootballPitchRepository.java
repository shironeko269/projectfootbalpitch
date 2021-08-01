package com.edu.fud.projectfootbalpitch.repository;

import com.edu.fud.projectfootbalpitch.entity.FootballPitchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FootballPitchRepository extends JpaRepository<FootballPitchEntity,Long> {
    @Query(value = "select f.* from football_pitchs f where f.ward_id=:WardId",nativeQuery = true)
    List<FootballPitchEntity> findAllByWardAndName(long WardId);

    @Query(value = "select s.* from football_pitchs s where id=(select max(id)from football_pitchs)",nativeQuery = true)
    FootballPitchEntity findByIdMax();

    //@Query(value = "select f.*,u.id,u.fullname from football_pitchs f inner join user u on f.user_id=u.id",nativeQuery = true)
    List<FootballPitchEntity> findAll();

    @Query(value = "select f.* from football_pitchs f where f.street_number=:streetNumber",nativeQuery = true)
    Optional<FootballPitchEntity> findAllStreetNumber(String streetNumber);

    @Query(value = "select f.* from football_pitchs f where f.urlmap=:urlMap",nativeQuery = true)
    Optional<FootballPitchEntity> findAllUrlMap(String urlMap);

    @Query(value = " select f.street_number from football_pitchs f where f.id=:pitchId",nativeQuery = true)
    String findStreetNumberByPitchId(long pitchId);

    @Query(value = " select f.urlmap from football_pitchs f where f.id=:pitchId",nativeQuery = true)
    String findUrlMapByPitchId(long pitchId);

    @Query(value = "select count(id) from football_pitchs",nativeQuery = true)
    int TotalFootballPitchs();
    @Query(value = "select fl.*,count(book.id) as soLuongDon " +
            "from football_pitchs fl inner join sub_pitch s inner join football_pitchs_schedule fn\n" +
            " inner join book_football_pitch book\n" +
            "on fl.id=s.pitch_pitch_id and s.id=fn.sub_pitch_id where " +
            "fn.id=book.football_pitch_schedule_id group by fl.id \n" +
            "order by count(fl.id) desc limit 5",nativeQuery = true)
    List<FootballPitchEntity> findAllTop5PitchBigBookingMost();
    @Query(value = "select count(book.id) as soLuongDon from football_pitchs fl " +
            "inner join sub_pitch s inner join football_pitchs_schedule fn\n" +
            " inner join book_football_pitch book\n" +
            "on fl.id=s.pitch_pitch_id and s.id=fn.sub_pitch_id and fn.id=book.football_pitch_schedule_id" +
            " where fl.id=:pitchId  group by fl.id \n" +
            "order by count(fl.id) desc;",nativeQuery = true)
    int findCountBookingByFootballPitchBig(long pitchId);
}
