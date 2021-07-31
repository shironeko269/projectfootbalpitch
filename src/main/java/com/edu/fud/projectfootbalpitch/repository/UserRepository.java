package com.edu.fud.projectfootbalpitch.repository;

import com.edu.fud.projectfootbalpitch.entity.BookFootballPitchEntity;
import  com.edu.fud.projectfootbalpitch.entity.UserEntity;
import com.edu.fud.projectfootbalpitch.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    @Query("select u from UserEntity u where u.userName = :userName")
    UserEntity findOneByUserName(String userName);
    @Query(value = "select r.* from user r where not exists (select * from football_pitchs s where s.user_id=r.id )and r.role='ROLE_MANAGER'",nativeQuery = true)
    List<UserEntity> findAllByRoleAndNoManagerPitch();
    @Query("select u from UserEntity u where u.userName = :userName")
    Optional<UserEntity> getOneByUserName(String userName);

    @Query(value = "select u.*,count(b.user_id) as solanquaylai FROM user u inner join \n" +
            "book_football_pitch b inner join football_pitchs_schedule f inner join \n" +
            "sub_pitch sp inner join football_pitchs fp inner join user ua on u.id=b.user_id \n" +
            "and b.football_pitch_schedule_id=f.id and fp.id=sp.pitch_pitch_id and f.sub_pitch_id=sp.id \n" +
            "and fp.user_id = ua.id where ua.id =:userId group by b.user_id order by solanquaylai desc",nativeQuery = true)
    List<UserEntity> findAllUserByReturnByManager(long userId);

    @Query(value = "select solanquaylai FROM (select u.*,count(b.user_id) as solanquaylai FROM user u inner join \n" +
            "book_football_pitch b inner join football_pitchs_schedule f inner join \n" +
            "sub_pitch sp inner join football_pitchs fp inner join user ua on u.id=b.user_id \n" +
            "and b.football_pitch_schedule_id=f.id and fp.id=sp.pitch_pitch_id and f.sub_pitch_id=sp.id \n" +
            "and fp.user_id = ua.id where ua.id =:userId group by b.user_id order by solanquaylai desc) so\n" +
            "where so.username=:username",nativeQuery = true)
    int numberUserReturnMost(long userId,String username);
}