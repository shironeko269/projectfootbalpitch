package com.edu.fud.projectfootbalpitch.repository;

import  com.edu.fud.projectfootbalpitch.entity.UserEntity;
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
    @Query("select u from UserEntity u where u.gmail = :gmail")
    Optional<UserEntity> getOneByGmail(String gmail);
    @Query(value = "select u.* from football_pitchs f inner join user u on f.user_id=u.id  where f.id=(select max(id)from football_pitchs)",nativeQuery = true)
    UserEntity findByUserMax();
    @Query(value = "select r.* from user r where r.id=:userId or r.role='ROLE_MANAGER' and not exists (select * from football_pitchs s where s.user_id=r.id )",nativeQuery = true)
    List<UserEntity> findAllUserRoleManagerAndUserId(long userId);
    @Query(value = "select u.* from user u where u.role='ROLE_MANAGER'",nativeQuery = true)
    List<UserEntity> findAndUserByRoleManager();
    @Query(value = "select u.*,count(o.id) as SoLuongDonHang from user u inner join orders o on u.id=o.user_id " +
            "group by u.id having count(o.id) order by count(o.id) desc",nativeQuery = true)
    List<UserEntity> findAllUserMostOrder();
    @Query(value = "select count(id) from user",nativeQuery = true)
    int TotalUsers();
    @Query(value = "select u.*,count(b.id) as SoLanQuayLai from user u inner join " +
            "book_football_pitch b on u.id=b.user_id \n" +
            "group by u.id having count(b.id) order by count(b.id) desc limit 5",nativeQuery = true)
    List<UserEntity> findAllBookingLimit5ByUser();
}