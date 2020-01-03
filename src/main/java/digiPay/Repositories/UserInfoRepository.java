package digiPay.Repositories;

import digiPay.Models.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {

    Boolean existsByUsername(String username);

    UserInfo findByUsername(String username);

}
